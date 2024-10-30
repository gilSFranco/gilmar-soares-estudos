package com.compass.aws_springboot.infra.mqueue;

import com.compass.aws_springboot.domain.model.ServiceStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendingRabbitmqPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void showStatus(ServiceStatus data) throws JsonProcessingException {
        String json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    private String convertIntoJson(ServiceStatus data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
