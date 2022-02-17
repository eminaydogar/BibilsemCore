package com.project.controller;

import java.util.HashMap;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bean.PrizeBean;
import com.project.controller.url.PrizeURL;
import com.project.entity.BBResponse;



@RequestMapping(PrizeURL.basePath)
public interface IPrizeController {
	@PostMapping(value = PrizeURL.save)
	BBResponse<?> save(@RequestBody PrizeBean bean);

	@PostMapping(value = PrizeURL.update)
	BBResponse<?> update(@RequestBody PrizeBean bean);

	@GetMapping(value = PrizeURL.refresh)
	HashMap<String, String> refresh();

	@PostMapping(value = PrizeURL.inactive)
	BBResponse<?> inactive(@PathVariable Long id);
}
