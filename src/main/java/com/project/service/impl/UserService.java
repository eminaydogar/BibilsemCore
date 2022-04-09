package com.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.cache.BBConstant.CHANNEL_TYPE;
import com.project.cache.BBConstant.MESSAGE_TYPE;
import com.project.cache.BBConstant.TIME_TYPE;
import com.project.cache.BBConstant.USER_VERIFICATION_TYPE;
import com.project.common.bean.AnswerBean;
import com.project.common.bean.LoginBean;
import com.project.common.bean.RegisterBean;
import com.project.common.bean.UserCouponBean;
import com.project.common.bean.UserPrizeRequestBean;
import com.project.common.bean.UserUpdateBean;
import com.project.common.bean.UserVerificationBean;
import com.project.common.dto.UserDto;
import com.project.entity.BBResponse;
import com.project.entity.CouponDefinition;
import com.project.entity.MessageDefinition;
import com.project.entity.PrizeDefinition;
import com.project.entity.PrizeRequestDefinition;
import com.project.entity.QuestionAnswerDefinition;
import com.project.entity.QuestionDefinition;
import com.project.entity.UserDefinition;
import com.project.exception.AuthorizationException;
import com.project.exception.EntityNotFoundException;
import com.project.exception.EntityValidationException;
import com.project.exception.ServiceOperationException;
import com.project.mail.MailContent;
import com.project.repository.UserRepository;
import com.project.security.PasswordCrypter;
import com.project.service.BaseService;
import com.project.service.IUserService;
import com.project.service.SQLCache;
import com.project.utility.LoggerUtility;
import com.project.utility.ObjectUtilty;
import com.project.utility.QueryBuilder;
import com.project.utility.SecureUtility;

@Service
public class UserService extends BaseService implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserRepository repo;

	private BBResponse<UserDto> response;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public BBResponse<UserDto> register(RegisterBean bean) {
		response = new BBResponse<>();
		UserDto userDto = null;
		try {
			ObjectUtilty.JSONValidation(bean);
			String username = bean.getUsername();
			String email = bean.getEmail();
			boolean isExist = repo.existsByUsernameOrEmail(username, email);
			if (isExist) {
				throw new ServiceOperationException("username or email has already exist");
			}
			UserDefinition user = repo.save(bean.toEntity());
			boolean result = verificationByMail(user);
			if (!result) {
				throw new ServiceOperationException("User has saved, but not vertified");
			}
			userDto = new UserDto(user);

		} catch (Exception e) {
			logger.error("register service error --" + e.getMessage());
			getCoreContainerService().getCoreManager()
					.saveOrUpdate(LoggerUtility.createLoggerSQL(getClass(), "register", e));
			response.setFaildResponse(e);
			return response;
		}
		response.setSuccessResponse(userDto);
		return response;
	}

	@Override
	public BBResponse<UserDto> confirmUser(UserVerificationBean bean) {
		UserDto userDto = null;
		response = new BBResponse<UserDto>();
		try {
			ObjectUtilty.JSONValidation(bean);
			/*
			 * MessageDefinition message = select(MessageDefinition.class,
			 * SQLCache.SELECT.MESSAGE_DEFINITION_VERIFICATION, bean.getUserId(), new
			 * Date());
			 */
			MessageDefinition message = getCoreContainerService().getCoreManager().get(MessageDefinition.class,
					SQLCache.SELECT.MESSAGE_DEFINITION_VERIFICATION, bean.getUserId(), new Date());
			if (bean.getVerificationCode().equalsIgnoreCase(message.getMessageContent())) {
				UserDefinition user = repo.findById(bean.getUserId()).orElse(null);
				if (user == null) {
					throw new EntityNotFoundException("User not found");
				} else {
					user.setVertify(USER_VERIFICATION_TYPE.VERIFIED);
					user = repo.saveAndFlush(user);
					userDto = new UserDto().single(user);

				}
			} else {
				throw new ServiceOperationException("Verification code match failed");
			}

		} catch (Exception e) {
			logger.error("[confirmUser]" + e.getMessage());
			response.setFaildResponse(e);
			return response;
		}
		response.setSuccessResponse(userDto);
		return response;
	}

	@Override
	public BBResponse<UserDto> login(LoginBean bean) {
		response = new BBResponse<>();
		UserDto userDto = null;
		try {
			ObjectUtilty.JSONValidation(bean);
			String username = bean.getUsername();
			UserDefinition user = repo.findByUsername(username);
			if (user == null) {
				throw new EntityNotFoundException("User not found. Please check username or password");
			}
			if (!PasswordCrypter.instance().matches(bean.getPassword(), user.getPassword())) {
				throw new EntityNotFoundException("User not found. Please check username or password");
			}
			user.setLastLoginDate(new Date());
			user = repo.saveAndFlush(user);
			userDto = new UserDto(user);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}
		response.setSuccessResponse(userDto);
		return response;
	}

	@Override
	public BBResponse<UserDto> saveCoupon(UserCouponBean bean) {
		response = new BBResponse<>();
		UserDto messageObj = null;
		try {
			ObjectUtilty.JSONValidation(bean);
			UserDefinition user = findByIdAuthorization(bean.getUserId());
			if (user == null) {
				throw new EntityNotFoundException("Entity not found.");
			}
			CouponDefinition coupon = bean.toEntity();
			fillQuestionAnswers(bean, coupon);
			balanceInquiry(user, coupon);
			inconsistentBalance(coupon);
			user.getCoupons().add(coupon);
			user = repo.saveAndFlush(user);
			messageObj = new UserDto().single(user);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}
		response.setSuccessResponse(messageObj);
		return response;
	}

	@Override
	public BBResponse<UserDto> savePrizeRequest(UserPrizeRequestBean bean) {
		response = new BBResponse<>();
		UserDto messageObj = null;
		try {
			ObjectUtilty.JSONValidation(bean);
			UserDefinition user = findByIdAuthorization(bean.getUserId());
			if (user == null) {
				throw new EntityNotFoundException("Entity not found.");
			}
			PrizeRequestDefinition prizeRequest = bean.toEntity();
			fillPrize(bean, prizeRequest);
			balanceInquiry(user, prizeRequest.getPrize());
			user.getPrizes().add(prizeRequest);
			user = repo.saveAndFlush(user);
			messageObj = new UserDto().single(user);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}
		response.setSuccessResponse(messageObj);
		return response;
	}

	@Override
	public BBResponse<UserDto> updateUser(UserUpdateBean bean) {
		response = new BBResponse<>();
		UserDto messageObj = null;
		try {
			ObjectUtilty.JSONValidation(bean);
			UserDefinition user = findByIdAuthorization(bean.getUserId());
			if (user == null) {
				throw new EntityNotFoundException("Entity not found.");
			}
			fillEntity4Update(bean, user);
			user = repo.saveAndFlush(user);
			messageObj = new UserDto().single(user);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}
		response.setSuccessResponse(messageObj);
		return response;
	}

	@Override
	public BBResponse<List<UserDto>> findWealthyUsers() {
		BBResponse<List<UserDto>> response = null;
		List<UserDto> resultList = new ArrayList<UserDto>();
		try {

			response = new BBResponse<>();
			List<UserDefinition> userList = repo.findTop3ByOrderByBbPointDesc();
			for (UserDefinition user : userList) {
				UserDto resultUser = new UserDto().single(user);
				resultUser.setPassword(null);
				resultUser.setPhoneNumber(null);
				resultUser.setEmail(null);
				resultUser.setId(null);
				resultList.add(resultUser);
			}
			response.setSuccessResponse(resultList);

		} catch (Exception e) {
			logger.error("findWealthyUsers error", e);
			response.setFaildResponse(e);
		}

		return response;
	}

	//////////////////////////////////////////// SERVICE @Override
	//////////////////////////////////////////// FINISHED////////////////////////////////////////////////////////////////////////////////

	private boolean verificationByMail(UserDefinition user) {
		try {
			if (user == null) {
				throw new EntityNotFoundException("User not found");
			}
			if (user.getVertify().equalsIgnoreCase("Y")) {
				throw new EntityValidationException("User has already vertifed");
			}
			Long userId = user.getId();
			MessageDefinition mailMessage = new MessageDefinition();
			mailMessage.setUserId(userId);
			mailMessage.setCdate(new Date());
			Date endDate = ObjectUtilty.createNextDate(TIME_TYPE.MINUTE, 5);
			String messageCode = SecureUtility.getInstance().generateVertifyCode(6);
			mailMessage.setEdate(endDate);
			mailMessage.setMessageContent(messageCode);
			mailMessage.setChannelType(CHANNEL_TYPE.MAIL);
			mailMessage.setMessageType(MESSAGE_TYPE.VERIFICATION);
			mailMessage.setMailTo(user.getEmail());
			/*
			 * getMailSender().sendMail(user.getEmail(), MailContent.registerSubject,
			 * MailContent.registerMessage + messageCode); save(mailMessage);
			 */
			getCoreContainerService().getMailService().sendMail(user.getEmail(), MailContent.registerSubject,
					MailContent.registerMessage + messageCode);
			getCoreContainerService().getCoreManager().save(mailMessage);
		} catch (Exception e) {
			logger.error("[verificationByMail] " + e);
			return false;
		}
		return true;

	}

	private UserDefinition findByIdAuthorization(Long id) throws AuthorizationException {
		if (id == null) {
			return null;
		}
		UserDefinition user = repo.findById(id).orElse(null);
		authorized(user);
		return user;
	}

	private boolean balanceInquiry(UserDefinition user, CouponDefinition coupon) throws ServiceOperationException {
		if (user.getBbPoint() > coupon.getAmount()) {
			Double newBalance = user.getBbPoint() - coupon.getAmount();
			user.setBbPoint(newBalance);
			return true;
		}

		else
			throw new ServiceOperationException("YETERSİZ BAKİYE");
	}

	private boolean balanceInquiry(UserDefinition user, PrizeDefinition prize) throws ServiceOperationException {
		if (prize == null) {
			throw new EntityNotFoundException("Prize not found");
		}
		if (user.getBbPoint() > prize.getPrice()) {
			Double newBalance = user.getBbPoint() - prize.getPrice();
			user.setBbPoint(newBalance);
			return true;
		}

		else
			throw new ServiceOperationException("YETERSİZ BAKİYE");
	}

	private boolean inconsistentBalance(CouponDefinition coupon) throws EntityValidationException {
		Double totalPrice = 1D;
		for (QuestionAnswerDefinition answer : coupon.getDetails()) {
			QuestionDefinition question = answer.getQuestion();
			if (answer.getAnswer().equalsIgnoreCase("Y")) {
				totalPrice *= question.getYesPrice();
			} else {
				totalPrice *= question.getNoPrice();
			}
		}
		if (coupon.getAmount() * totalPrice != coupon.getPrice()) {
			throw new EntityValidationException("TUTARSIZ KUPON BAKİYESİ");
		}
		return true;
	}

	private void fillEntity4Update(UserUpdateBean bean, UserDefinition user) {
		if (bean.getEmail() != null) {
			user.setEmail(bean.getEmail());
		}
		if (bean.getAddress() != null) {
			user.setAddress(bean.getAddress());
		}
		if (bean.getPhoneNumber() != null) {
			user.setPhoneNumber(bean.getPhoneNumber());
		}
		if (bean.getPassword() != null) {
			if (bean.getOldPassword() != null
					&& PasswordCrypter.instance().matches(bean.getOldPassword(), user.getPassword())) {
				user.setPassword(PasswordCrypter.instance().encode(bean.getPassword()));
			}
		}

	}

	private void fillQuestionAnswers(UserCouponBean bean, CouponDefinition coupon) throws ServiceOperationException {
		Long[] questionIds = new Long[bean.getAnswers().size()];
		int counter = 0;
		for (AnswerBean answerBean : bean.getAnswers()) {
			questionIds[counter] = answerBean.getQuestionId();
			counter++;
		}
		QueryBuilder queryBuilder = new QueryBuilder("Select * from question_definition where status='Y' and id=? ");
		queryBuilder.setParams(questionIds);
		List<QuestionDefinition> questionDefinitionList = getCoreContainerService().getCoreManager()
				.getList(QuestionDefinition.class, queryBuilder.toString());
		if (questionDefinitionList == null || questionDefinitionList.size() != bean.getAnswers().size()) {
			throw new ServiceOperationException("Soru listesinde pasif kayıtlar mevcut");
		}
		Set<QuestionAnswerDefinition> questionAnswerEntity = new HashSet<QuestionAnswerDefinition>();
		for (AnswerBean answerBean : bean.getAnswers()) {
			for (QuestionDefinition question : questionDefinitionList) {
				if (answerBean.getQuestionId() == question.getId()) {
					QuestionAnswerDefinition answerDefinition = new QuestionAnswerDefinition();
					answerDefinition.setAnswer(answerBean.getAnswer());
					answerDefinition.setQuestion(question);
					questionAnswerEntity.add(answerDefinition);
					break;
				}

			}
		}
		coupon.setDetails(questionAnswerEntity);

	}

	private void fillPrize(UserPrizeRequestBean bean, PrizeRequestDefinition prizeRequest)
			throws EntityNotFoundException {
		PrizeDefinition prize = getCoreContainerService().getCoreManager().get(PrizeDefinition.class,
				"Select * from prize_definition where status ='Y' and id=?", bean.getPrizeId());
		if (prize == null) {
			throw new EntityNotFoundException("Aktif hediye kaydı bulunamadı");
		}
		prizeRequest.setPrize(prize);
	}

}
