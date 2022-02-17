package com.project.controller.impl;
/*
 * package com.project.controller;
 * 
 * import org.springframework.amqp.rabbit.core.RabbitTemplate; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.project.dto.QuestionDto; import com.project.dto.UserDto; import
 * com.project.entity.BBResponse; import com.project.enums.OperationCODE; import
 * com.project.queue.MessagingConfig; import
 * com.project.service.IServiceExecutor; import com.project.service.UserService;
 * import com.project.utility.URL;
 * 
 * @RestController
 * 
 * @RequestMapping("api/test") public class QueueController { private final
 * IServiceExecutor<UserDto> service; private final RabbitTemplate template;
 * 
 * public QueueController(RabbitTemplate template, UserService service) {
 * this.template = template; this.service = service; }
 * 
 * @GetMapping(value = "addqueue") public String addQueue() { UserDto dto = new
 * UserDto(); dto.setId(1L);
 * template.convertAndSend(MessagingConfig.userExchange,
 * MessagingConfig.userRoutingKey, dto);
 * 
 * return "isOk"; }
 * 
 * private UserDto injectPathVariableToDto(Object value) { UserDto dto = new
 * UserDto(); if (value instanceof Long) { dto.setId((long) value); } else if
 * (value instanceof String) { dto.setUsername(value.toString()); } return dto;
 * }
 * 
 * }
 */