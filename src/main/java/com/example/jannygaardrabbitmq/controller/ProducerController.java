package com.example.jannygaardrabbitmq.controller;

import com.example.jannygaardrabbitmq.service.RabbitMQSender;
import com.example.jannygaardrabbitmq.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProducerController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @PostMapping("/webhook/td/booking/create/{id}")
    public String td(@PathVariable int id) {
        rabbitMQSender.sendTd(id);
        return "Sent to TD " + id;
    }

    @PostMapping("/rabbitmq/sp")
    public String sp() {
        rabbitMQSender.sendSp("Message to SP");
        return "Sent to SP";
    }
}
