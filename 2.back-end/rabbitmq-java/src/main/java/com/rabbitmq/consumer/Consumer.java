package com.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.rabbitmq.config.ConsumerConfig.QUEUE_NAME;

@Component
public class Consumer {

    @RabbitListener(queues = QUEUE_NAME)
    public void listener (Message message, String assunto){
        System.out.println("conteudo foi consumido da fila: " + assunto);
    }
    
}