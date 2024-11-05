package com.compasso.msnotify.infra.mqueue;

import com.compasso.msnotify.entities.Notify;
import com.compasso.msnotify.service.NotifyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ListenerRabbitmqSubscriber {

    private final NotifyService notifyService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Notify notify = mapper.readValue(payload, Notify.class);

        notifyService.insert(notify);

        log.info(
                String.format("The username %s made an request to: %s", notify.getUsername(), notify.getOperation())
        );
    }
}
