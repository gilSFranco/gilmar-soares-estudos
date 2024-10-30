package com.compass.aws_springboot.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ListenerRabbitmqSubscriber {
    @RabbitListener(queues = "${mq.queues.listener-rabbitmq}")
    public void listen(@Payload String payload) {
        System.out.println(payload);
    }
}
