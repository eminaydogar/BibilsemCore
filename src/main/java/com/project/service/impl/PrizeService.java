package com.project.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bean.PrizeBean;
import com.project.cache.BBConstant.RECORD_STATUS;
import com.project.cache.PrizeCache;
import com.project.dto.PrizeDto;
import com.project.entity.BBResponse;
import com.project.entity.PrizeDefinition;
import com.project.exception.EntityNotFoundException;
import com.project.exception.EntityValidationException;
import com.project.repository.PrizeRepository;
import com.project.service.BaseService;
import com.project.service.IPrizeService;
import com.project.utility.ObjectUtilty;

@Service
public class PrizeService extends BaseService implements IPrizeService {

	private final PrizeRepository repo;
	private BBResponse<PrizeDto> response;

	public PrizeService(PrizeRepository repo) {
		this.repo = repo;
	}

	@Override
	public BBResponse<PrizeDto> save(PrizeBean bean) {
		response = new BBResponse<>();
		PrizeDto messageObj = null;
		try {
			ObjectUtilty.JSONValidation(bean);
			PrizeDefinition prize = repo.save(bean.toEntity());
			PrizeCache.getContext().set(prize);
			messageObj = new PrizeDto(prize);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}

		response.setSuccessResponse(messageObj);
		return response;

	}

	@Override
	public BBResponse<PrizeDto> update(PrizeBean bean) {
		response = new BBResponse<>();
		PrizeDto messageObj = null;
		try {
			if (bean.getPrizeId() == null) {
				throw new EntityValidationException("id cannot be null");
			}
			PrizeDefinition prize = repo.findById(bean.getPrizeId()).orElse(null);
			if (prize == null) {
				throw new EntityNotFoundException("Prize not found");
			}
			fillEntity4Update(bean, prize);
			prize = repo.saveAndFlush(prize);
			PrizeCache.getContext().set(prize);
			messageObj = new PrizeDto(prize);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}

		response.setSuccessResponse(messageObj);
		return response;
	}

	@Override
	public HashMap<String, String> cacheRefresh() {
		HashMap<String, String> resultMap = new HashMap<>();
		String messageObj = null;
		try {
			List<PrizeDefinition> questionList = repo.findAllByStatus("Y");
			PrizeCache.getContext().set(questionList);
		} catch (Exception e) {
			messageObj = "Refresh fail!! Because of " + e.getMessage();
		}
		messageObj = "Refresh completed successfully";
		resultMap.put("RESULT", messageObj);
		return resultMap;

	}

	@Override
	public BBResponse<PrizeDto> inactive(Long prizeId) {
		response = new BBResponse<>();
		PrizeDto messageObj = null;
		try {
			PrizeDefinition prize = repo.findById(prizeId).orElse(null);
			if (prize == null) {
				throw new EntityNotFoundException("Prize not found");
			}
			prize.setStatus(RECORD_STATUS.NOK);
			prize = repo.saveAndFlush(prize);
			PrizeCache.getContext().remove(prize);
			messageObj = new PrizeDto(prize);
		} catch (Exception e) {
			response.setFaildResponse(e);
			return response;
		}

		response.setSuccessResponse(messageObj);
		return response;
	}

	private void fillEntity4Update(PrizeBean bean, PrizeDefinition prize) {
		if (bean.getPrice() != null) {
			prize.setPrice(bean.getPrice());
		}
		if (bean.getPrizeName() != null) {
			prize.setPrizeName(bean.getPrizeName());
		}
		if (bean.getPrizeImage() != null) {
			prize.setPrizeImage(bean.getPrizeImage());
		}
	}

}
