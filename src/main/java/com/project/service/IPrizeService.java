package com.project.service;

import java.util.HashMap;

import com.project.bean.PrizeBean;
import com.project.dto.PrizeDto;
import com.project.entity.BBResponse;

public interface IPrizeService {
	public BBResponse<PrizeDto> save(PrizeBean bean);
	public BBResponse<PrizeDto> update(PrizeBean bean);
	public HashMap<String, String> cacheRefresh();
	public BBResponse<PrizeDto> inactive(Long prizeId);
	
}
