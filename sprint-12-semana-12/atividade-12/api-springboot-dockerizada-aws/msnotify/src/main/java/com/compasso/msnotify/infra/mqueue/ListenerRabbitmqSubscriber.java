package com.compasso.msnotify.infra.mqueue;

import com.compasso.msnotify.entities.Notify;
import com.compasso.msnotify.service.NotifyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ListenerRabbitmqSubscriber {

    private final NotifyService notifyService;

    @RabbitListener(queues = "${mq.queues.listener-rabbitmq}")
    public void listen(@Payload String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Notify notify = mapper.readValue(payload, Notify.class);

        notifyService.insert(notify);

        System.out.println(notify);
    }
}
