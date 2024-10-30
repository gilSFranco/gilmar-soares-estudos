package com.compass.aws_springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Value("${mq.queues.listener-rabbitmq}")
    private String listenerRabbitmqQueue;

    @Bean
    public Queue queueServiceStatus() {
        return new Queue(listenerRabbitmqQueue, true);
    }
}
