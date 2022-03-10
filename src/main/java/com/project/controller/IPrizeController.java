package com.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.common.bean.PrizeBean;
import com.project.controller.url.PrizeURL;
import com.project.entity.BBResponse;

import io.swagger.annotations.Api;


@Api(tags = "Prize Service",value = "Info")
@RequestMapping(PrizeURL.basePath)
public interface IPrizeController {
	
	
	@GetMapping(value=PrizeURL.getAll)
	BBResponse<?> getAll();

	
	@PostMapping(value = PrizeURL.save)
	BBResponse<?> save(@RequestBody PrizeBean bean);

	@PostMapping(value = PrizeURL.update)
	BBResponse<?> update(@RequestBody PrizeBean bean);

	@GetMapping(value = PrizeURL.refresh)
	Map<String, Object> refresh();

	@PostMapping(value = PrizeURL.inactive)
	BBResponse<?> inactive(@PathVariable Long id);
}
