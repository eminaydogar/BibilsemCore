/*
 * package com.project.queue;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.amqp.core.AmqpTemplate; import
 * org.springframework.amqp.core.Binding; import
 * org.springframework.amqp.core.BindingBuilder; import
 * org.springframework.amqp.core.Queue; import
 * org.springframework.amqp.core.TopicExchange; import
 * org.springframework.amqp.rabbit.connection.ConnectionFactory; import
 * org.springframework.amqp.rabbit.core.RabbitTemplate; import
 * org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
 * import org.springframework.amqp.support.converter.MessageConverter;
 * 
 * @Configuration public class MessagingConfig {
 * 
 * public static final String userQueue="user_queue_0"; public static final
 * String userExchange="user_exchange_0"; public static final String
 * userRoutingKey="user_routing_key_0";
 * 
 * @Bean public Queue queue() { return new Queue(userQueue); }
 * 
 * @Bean public TopicExchange exchange() { return new
 * TopicExchange(userExchange); }
 * 
 * @Bean public Binding bind(Queue queue, TopicExchange exchange) { return
 * BindingBuilder.bind(queue).to(exchange).with(userRoutingKey); }
 * 
 * @Bean public MessageConverter converter() { return new
 * Jackson2JsonMessageConverter(); }
 * 
 * public AmqpTemplate template(ConnectionFactory connectionFactory) { final
 * RabbitTemplate rabitTemplate = new RabbitTemplate(connectionFactory);
 * rabitTemplate.setMessageConverter(converter()); return rabitTemplate; }
 * 
 * }
 */