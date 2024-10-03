package com.example.jannygaardrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.td}")
    private String TD_QUEUE;

    @Value("${rabbitmq.queue.sp}")
    private String SP_QUEUE;

    @Value("${rabbitmq.routing.key.td}")
    private String routingKeyTd;

    @Value("${rabbitmq.routing.key.sp}")
    private String routingKeySp;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Bean
    public Queue tdQueue() {
        return new Queue(TD_QUEUE, true);
    }

    @Bean
    public Queue spQueue() {
        return new Queue(SP_QUEUE, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding tdBinding(Queue tdQueue, TopicExchange exchange) {
        return BindingBuilder.bind(tdQueue).to(exchange).with(routingKeyTd);
    }

    @Bean
    public Binding spBinding(Queue spQueue, TopicExchange exchange) {
        return BindingBuilder.bind(spQueue).to(exchange).with(routingKeySp);
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        //cachingConnectionFactory.setUsername(username);
        //cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }




}
