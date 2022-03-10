package com.project.service;

import java.util.List;
import java.util.Map;

import com.project.common.bean.PrizeBean;
import com.project.common.dto.PrizeDto;
import com.project.entity.BBResponse;

public interface IPrizeService {
	public BBResponse<List<PrizeDto>> getAll();
	public BBResponse<PrizeDto> save(PrizeBean bean);
	public BBResponse<PrizeDto> update(PrizeBean bean);
	public Map<String, Object> cacheRefresh();
	public BBResponse<PrizeDto> inactive(Long prizeId);
	
}
