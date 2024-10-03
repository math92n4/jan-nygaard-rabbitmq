package com.example.jannygaardrabbitmq.Service;

import com.example.jannygaardrabbitmq.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "${rabbitmq.queue.td}")
    public void receivedMessageTd(Car car) {
        logger.info("RECEIVED MESSAGE FROM TD");
    }

    @RabbitListener(queues = "${rabbitmq.queue.sp}")
    public void receivedMessageSp(Car car) {
        logger.info("RECEIVED MESSAGE FROM SP");
    }
}
