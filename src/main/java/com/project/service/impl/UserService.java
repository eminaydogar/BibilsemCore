package com.project.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.cache.BBConstant.CHANNEL_TYPE;
import com.project.cache.BBConstant.MESSAGE_TYPE;
import com.project.cache.BBConstant.TIME_TYPE;
import com.project.cache.BBConstant.USER_VERIFICATION_TYPE;
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
			getCoreContainerService().getQueryManager().saveOrUpdate(LoggerUtility.createLoggerSQL(getClass(),"register",e));
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
			/*MessageDefinition message = select(MessageDefinition.class, SQLCache.SELECT.MESSAGE_DEFINITION_VERIFICATION,
					bean.getUserId(), new Date()); */
			MessageDefinition message = getCoreContainerService().getQueryManager().get(MessageDefinition.class,
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
			/*getMailSender().sendMail(user.getEmail(), MailContent.registerSubject,
					MailContent.registerMessage + messageCode);
			save(mailMessage); */
			getCoreContainerService().getMailService().sendMail(user.getEmail(), MailContent.registerSubject,
					MailContent.registerMessage + messageCode);
			getCoreContainerService().getQueryManager().save(mailMessage);
		} catch (Exception e) {
			logger.error("[verificationByMail] " + e);
			return false;
		}
		return true;

	}

	private UserDefinition findByIdAuthorization(Long id) throws AuthorizationException {
		if(id==null) {
			return null;
		}
		UserDefinition user = repo.findById(id).orElse(null);
		authorized(user);
		return user;
	}

	private boolean balanceInquiry(UserDefinition user, CouponDefinition coupon) throws ServiceOperationException {
		if (user.getBbPoint() > coupon.getAmount()) {
			Long newBalance = user.getBbPoint() - coupon.getAmount();
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
			Long newBalance = user.getBbPoint() - prize.getPrice();
			user.setBbPoint(newBalance);
			return true;
		}

		else
			throw new ServiceOperationException("YETERSİZ BAKİYE");
	}

	private boolean inconsistentBalance(CouponDefinition coupon) throws EntityValidationException {
		Long totalPrice = 1L;
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

	// first select in UserService for authenticate

}

/*
 * @Override public BBResponse<UserDto> execute(OperationCODE operationCode,
 * Object request) { response = new BBResponse<UserDto>(); UserDto userDto =
 * null;
 * 
 * try {
 * 
 * switch (operationCode) { case USER_SELECT: Long id = (Long) request; userDto
 * = userSelect(id); break; case USER_INSERT: UserDto insert = (UserDto)
 * request; userDto = userInsert(insert); break; case USER_UPDATE: UserDto
 * update = (UserDto) request; userDto = userUpdate(update); break; case
 * USER_DELETE: UserDto delete = (UserDto) request; userDto =
 * userDelete(delete); break; case USER_COUPON_UPDATE: UserDto couponUpdate =
 * (UserDto) request; userDto = userCouponUpdate(couponUpdate); break; case
 * USER_ROLE_UPDATE: UserDto roleUpdate = (UserDto) request; userDto =
 * userRoleUpdate(roleUpdate); break; case USER_PRIZE_UPDATE: UserDto
 * prizeUpdate = (UserDto) request; userDto = userPrizeUpdate(prizeUpdate);
 * break; default: break; }
 * 
 * } catch (Exception e) { response.setFaildResponse(e); return response; }
 * 
 * response.setSuccessResponse(userDto); return response; }
 * 
 * 
 * /* // first select in UserService for authenticate
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { UserDto user = findByUsername(username); if (user
 * == null) throw new UsernameNotFoundException("User not found"); userDetails =
 * new CustomUserDetails(user); return userDetails;
 * 
 * }
 * 
 * private UserDto findByUsername(String username) { UserDefinition user =
 * dao.findByUsername(username); return new UserDto(user, false, false);
 * 
 * }
 * 
 * private UserDto userCouponUpdate(UserDto requestDto) throws
 * AuthorizationException, EntityValidationException, RequirementFieldException,
 * EntityNotFoundException {
 * 
 * UserDto dtoExist = selectUserWithAuthorizationControl(requestDto.getId());
 * CouponDto couponDto = DTOUtilty.forceSingleEntity(requestDto.getCoupons());
 * validateCoupon(dtoExist, couponDto); dtoExist.addCoupon(couponDto);
 * UserDefinition user = dtoExist.toEntity(); user = dao.update(user); return
 * new UserDto(user, true, false); }
 * 
 * private UserDto userUpdate(UserDto requestDto) throws AuthorizationException,
 * EntityNotFoundException { UserDto dtoExist =
 * selectUserWithAuthorizationControl(requestDto.getId());
 * dtoExist.setEmail(requestDto.getEmail());
 * dtoExist.setPhoneNumber(requestDto.getPhoneNumber());
 * dtoExist.setIsBlackList(requestDto.getIsBlackList());
 * dtoExist.setPassword(requestDto.getPassword()); UserDefinition user =
 * dtoExist.toEntity(); user = dao.update(user); return new UserDto(user, false,
 * false); }
 * 
 * private UserDto userSelect(String username) throws AuthorizationException,
 * EntityNotFoundException { UserDefinition user = dao.findByUsername(username);
 * if (user == null) { throw new EntityNotFoundException("User not found"); }
 * UserDto userDto = new UserDto(user); return userDto; }
 * 
 * private UserDto userSelect(Long id) throws AuthorizationException,
 * EntityNotFoundException { UserDefinition user = dao.select(id); if (user ==
 * null) { throw new EntityNotFoundException("User not found"); } UserDto
 * userDto = new UserDto(user); return userDto; }
 * 
 * // only admin private UserDto userInsert(UserDto requestDto) { UserDefinition
 * newUser = requestDto.toEntity(); newUser = dao.insert(newUser); return new
 * UserDto(newUser, false, false); }
 * 
 * // only admin private UserDto userRoleUpdate(UserDto requestDto) throws
 * AuthorizationException, RequirementFieldException, EntityNotFoundException {
 * DTOUtilty.JSONValidation(requestDto); UserDto dtoExist =
 * selectUserWithAuthorizationControl(requestDto.getId());
 * dtoExist.setRoles(requestDto.getRoles()); UserDefinition user =
 * dtoExist.toEntity(); user = dao.update(user); return new UserDto(user, false,
 * false); }
 * 
 * private UserDto userPrizeUpdate(UserDto userDto) throws
 * EntityValidationException, RequirementFieldException, AuthorizationException,
 * EntityNotFoundException {
 * 
 * UserDto dtoExist = selectUserWithAuthorizationControl(userDto.getId());
 * PrizeRequestDto prizeRequest =
 * DTOUtilty.forceSingleEntity(userDto.getPrizes());
 * validatePrizeRequest(dtoExist, prizeRequest);
 * dtoExist.addPrizeRequest(prizeRequest); UserDefinition user =
 * dtoExist.toEntity(); user = dao.update(user); return new UserDto(user, false,
 * true); }
 * 
 * // only admin private UserDto userDelete(UserDto model) { dao.delete(model);
 * return model; }
 * 
 * //// functions for security
 * 
 * private boolean authorizationVisibilityControl(UserDto defUser) throws
 * AuthorizationException { CustomUserDetails authorizedUser = userDetails; if
 * (authorizedUser == null || defUser == null) { throw new
 * AuthorizationException("No authorization"); } if
 * (defUser.getUsername().equalsIgnoreCase(authorizedUser.getUsername())) {
 * return true; } else { for (GrantedAuthority role :
 * authorizedUser.getAuthorities()) { if
 * (role.getAuthority().equalsIgnoreCase("ROLE_" +
 * UserRoleTYPE.ADMIN.getName())) { return true; } } throw new
 * AuthorizationException("No authorization"); }
 * 
 * }
 * 
 * private boolean validatePrizeRequest(UserDto user, PrizeRequestDto
 * prizeRequest) throws EntityValidationException, RequirementFieldException,
 * EntityNotFoundException {
 * 
 * DTOUtilty.JSONValidation(prizeRequest); PrizeDefinition cacheModel =
 * PrizeCache.getInstance().getById(prizeRequest.getId()); if (cacheModel ==
 * null) { throw new EntityNotFoundException("Entity not found"); } if
 * (user.getBbPoint() < cacheModel.getPrice()) { throw new
 * EntityValidationException("Insufficient balance"); }
 * 
 * return true; }
 * 
 * 
 * private boolean compareBalanceWithAmount(Long bbPoint, Long prizeId) throws
 * EntityValidationException { Long price = ((BigInteger)
 * dao.executeSingleResultQuery(SQLCache.selectPrizePrice, false,
 * prizeId)).longValue(); if (price > bbPoint) { throw new
 * EntityValidationException("The requested prize price cannot be more than BBpoint"
 * ); }
 * 
 * return true; }
 * 
 * 
 * private boolean validateCoupon(UserDto existDto, CouponDto couponDto) throws
 * EntityValidationException, RequirementFieldException, EntityNotFoundException
 * { Long bbPoint = existDto.getBbPoint(); if
 * (!DTOUtilty.JSONValidation(couponDto)) { throw new
 * RequirementFieldException("Insufficient values ​​entered for mandatory fields"
 * ); } if (couponDto.getPrice() > bbPoint) { throw new
 * EntityValidationException("Amount cannot be more than BBPoint"); }
 * couponAmountValidation(couponDto); return true; }
 * 
 * private void couponAmountValidation(CouponDto coupon) throws
 * RequirementFieldException, EntityValidationException, EntityNotFoundException
 * { Long totalPrice = 1L; for (QuestionAnswerDto dto : coupon.getDetails()) {
 * QuestionDefinition cacheModel =
 * QuestionCache.getInstance().getById(dto.getQuestion().getId()); if
 * (cacheModel == null) { throw new EntityNotFoundException("Entity not found");
 * // cacheModel = (QuestionDefinition) dao.executeSingleResultQuery("SELECT *
 * FROM // QUESTION_DEFINITION WHERE ID=?", dto.getId()); //
 * if(cacheModel==null) { // throw new
 * EntityNotFoundException("Entity not found"); // } } if
 * (dto.getAnswer().equalsIgnoreCase("Y")) { totalPrice *=
 * cacheModel.getYesPrice(); } else if (dto.getAnswer().equalsIgnoreCase("N")) {
 * totalPrice *= cacheModel.getNoPrice(); } else { throw new
 * EntityValidationException("Unacceptable value"); }
 * 
 * }
 * 
 * if (coupon.getAmount() / coupon.getPrice() != totalPrice) { throw new
 * EntityValidationException("Unacceptable value"); }
 * 
 * }
 * 
 * private UserDto selectUserWithAuthorizationControl(Long id) throws
 * AuthorizationException, EntityNotFoundException { UserDefinition userExist =
 * dao.select(id); if (userExist == null || userExist.getUsername() == null) {
 * throw new EntityNotFoundException("Kullanıcı bulunamadı"); } UserDto dtoExist
 * = new UserDto(userExist); authorizationVisibilityControl(dtoExist); return
 * dtoExist; }
 */

//

/*
 * 
 * 
 * DELIMITER $$ CREATE TRIGGER BBPointUpdate AFTER INSERT ON
 * bibilsem_schema.user_coupon_list FOR each row begin DECLARE dPrice bigint;
 * DECLARE dBBPoint bigint; SELECT coupon_price INTO dPrice FROM
 * coupon_definition where id = NEW.coupon_id; SELECT bbpoint INTO dBBPoint FROM
 * user_definition where id = NEW.user_id; IF(dBBPoint < dPrice ) THEN UPDATE
 * coupon_definition SET EDATE = NOW(),coupon_status='F' WHERE ID =
 * NEW.coupon_id; ELSE UPDATE user_definition SET bbpoint = bbpoint - dPrice
 * where id = NEW.user_id; END IF;
 * 
 * END$$ DELIMITER ;
 * 
 */
