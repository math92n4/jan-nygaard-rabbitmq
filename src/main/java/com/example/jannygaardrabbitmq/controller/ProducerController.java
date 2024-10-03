package com.example.jannygaardrabbitmq.controller;

import com.example.jannygaardrabbitmq.service.RabbitMQSender;
import com.example.jannygaardrabbitmq.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProducerController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @PostMapping("/td")
    public String td(@RequestBody Car car) {
        rabbitMQSender.sendTd(car);
        return "Sent to TD";
    }

    @PostMapping("/sp")
    public String sp(@RequestBody Car car) {
        rabbitMQSender.sendSp(car);
        return "Sent to SP";
    }
}
