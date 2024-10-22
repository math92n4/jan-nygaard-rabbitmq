package com.example.jannygaardrabbitmq.controller;

import com.example.jannygaardrabbitmq.service.RabbitMQSender;
import com.example.jannygaardrabbitmq.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProducerController {

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @PostMapping("/webhook/td/booking/create/{id}")
    public String td(@PathVariable int id) {
        rabbitMQSender.sendTd(id);
        logger.info("RABBITMQ MESSAGE TO TD: ", id);
        return "Sent to TD " + id;
    }

    @PostMapping("/rabbitmq/sp")
    public String sp() {
        rabbitMQSender.sendSp("Message to SP");
        logger.info("RABBITMQ MESSAGE TO SP");
        return "Sent to SP";
    }
}
