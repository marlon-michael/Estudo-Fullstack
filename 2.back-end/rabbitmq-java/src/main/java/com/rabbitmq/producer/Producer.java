package com.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import static com.rabbitmq.config.RabbitMQ.EXCHANGE_NAME;
import static com.rabbitmq.config.RabbitMQ.ROUTE_KEY;


@Component
@RequiredArgsConstructor
public class Producer {
    private final RabbitTemplate template;
    
    public void addToQueue(String request){
        template.convertAndSend(EXCHANGE_NAME, ROUTE_KEY, request);
    }
}
