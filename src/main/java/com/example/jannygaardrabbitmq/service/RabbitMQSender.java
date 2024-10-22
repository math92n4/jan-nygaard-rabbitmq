package com.example.jannygaardrabbitmq.service;

import com.example.jannygaardrabbitmq.entity.Car;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key.td}")
    private String routingKeyTd;

    @Value("${rabbitmq.routing.key.sp}")
    private String routingKeySp;


    public void sendTd(int id) {
        System.out.println(id);
        rabbitTemplate.convertAndSend(exchangeName, routingKeyTd, id);
    }

    public void sendSp(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKeySp, message);
    }

}
