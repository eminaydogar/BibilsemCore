/*
 * package com.project.queue;
 * 
 * import org.springframework.amqp.rabbit.annotation.RabbitListener; import
 * org.springframework.stereotype.Component;
 * 
 * import com.project.dto.IDto;
 * 
 * @Component public class MessagingConsumer {
 * 
 * 
 * 
 * @RabbitListener(queues = MessagingConfig.userQueue) public void
 * getMessage(IDto message) { System.out.println("Message is " + message);
 * 
 * } }
 */