package com.project.controller.impl;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.bean.PrizeBean;
import com.project.controller.IPrizeController;
import com.project.entity.BBResponse;
import com.project.service.IPrizeService;
import com.project.service.impl.PrizeService;

@RestController
public class PrizeController implements IPrizeController {

	private final IPrizeService service;

	public PrizeController(PrizeService service) {
		this.service = service;
	}

	@Override
	public BBResponse<?> save(PrizeBean bean) {
		return service.save(bean);
	}

	@Override
	public BBResponse<?> update(PrizeBean bean) {
		return service.update(bean);
	}

	@Override
	public Map<String, Object> refresh() {
		return service.cacheRefresh();
	}

	@Override
	public BBResponse<?> inactive(@PathVariable Long id) {
		return service.inactive(id);
	}

}
