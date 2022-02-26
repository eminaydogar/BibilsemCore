package com.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.cache.BBConstant.RECORD_STATUS;
import com.project.cache.QuestionCache;
import com.project.common.bean.QuestionBean;
import com.project.common.dto.QuestionDto;
import com.project.entity.BBResponse;
import com.project.entity.QuestionDefinition;
import com.project.exception.EntityNotFoundException;
import com.project.exception.EntityValidationException;
import com.project.repository.QuestionRepository;
import com.project.service.BaseService;
import com.project.service.IQuestionService;
import com.project.utility.HandleUtil;
import com.project.utility.ObjectUtilty;

@Service
public class QuestionService extends BaseService implements IQuestionService {

	private final QuestionRepository repo;
	private BBResponse<QuestionDto> response = null;

	public QuestionService(QuestionRepository repo) {
		this.repo = repo;
	}

	@Override
	public BBResponse<List<QuestionDto>> getAll() {
		BBResponse<List<QuestionDto>> response = new BBResponse<>();
		List<QuestionDto> messageObj = new ArrayList<QuestionDto>();
		try {
			List<QuestionDefinition> questionList = repo.findAllByStatus("Y");
			for (QuestionDefinition question : questionList) {
				messageObj.add(new QuestionDto(question));
			}

		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}

		response.setSuccessResponse(messageObj);
		return response;
	}

	@Override
	public BBResponse<QuestionDto> save(QuestionBean bean) {
		response = new BBResponse<>();
		QuestionDto messageObj = null;
		try {
			ObjectUtilty.JSONValidation(bean);
			QuestionDefinition question = repo.save(bean.toEntity());
			QuestionCache.getContext().set(question);
			messageObj = new QuestionDto(question);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}

		response.setSuccessResponse(messageObj);
		return response;

	}

	@Override
	public BBResponse<QuestionDto> update(QuestionBean bean) {
		response = new BBResponse<>();
		QuestionDto messageObj = null;
		try {
			if (bean.getQuestionId() == null) {
				throw new EntityValidationException("id cannot be null");
			}
			QuestionDefinition question = repo.findById(bean.getQuestionId()).orElse(null);
			if (question == null) {
				throw new EntityNotFoundException("Question not found");
			}
			fillEntity4Update(bean, question);
			question = repo.saveAndFlush(question);
			QuestionCache.getContext().set(question);
			messageObj = new QuestionDto(question);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}

		response.setSuccessResponse(messageObj);
		return response;

	}

	@Override
	public Map<String, Object> cacheRefresh() {
		Map<String, Object> responseMap = new HashMap<>();
		String messageObj = null;
		try {
			List<QuestionDefinition> questionList = repo.findAllByStatus("Y");
			QuestionCache.getContext().set(questionList);
			messageObj = "Refresh completed successfully";
			return HandleUtil.responseHandler(responseMap, messageObj);
		} catch (Exception e) {
			messageObj = "Refresh fail!! Because of " + e.getMessage();
			return HandleUtil.responseHandler(responseMap, messageObj, e);
		}

	}

	@Override
	public BBResponse<QuestionDto> inactive(Long questionId) {
		response = new BBResponse<>();
		QuestionDto messageObj = null;
		try {
			QuestionDefinition question = repo.findById(questionId).orElse(null);
			if (question == null) {
				throw new EntityNotFoundException("Question not found");
			}
			question.setStatus(RECORD_STATUS.NOK);
			question = repo.saveAndFlush(question);
			QuestionCache.getContext().remove(question.getId());
			messageObj = new QuestionDto(question);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}

		response.setSuccessResponse(messageObj);
		return response;
	}

	private void fillEntity4Update(QuestionBean bean, QuestionDefinition question) {
		if (bean.getAnswer() != null) {
			question.setAnswer(bean.getAnswer());
		}
		if (bean.getEdate() != null) {
			question.setEdate(bean.getEdate());
		}
		if (bean.getNoPrice() != null) {
			question.setNoPrice(bean.getNoPrice());
		}
		if (bean.getQuestionText() != null) {
			question.setQuestionText(bean.getQuestionText());
		}
		if (bean.getStatus() != null) {
			question.setStatus(bean.getStatus());
		}
		if (bean.getYesPrice() != null) {
			question.setYesPrice(bean.getYesPrice());
		}
	}

}
