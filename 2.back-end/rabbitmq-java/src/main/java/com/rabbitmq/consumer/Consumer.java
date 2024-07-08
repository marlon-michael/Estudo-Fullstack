package com.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    public static final String QUEUE_NAME = "fila.um";

    @RabbitListener(queues = QUEUE_NAME)
    public void listener (Message message, String assunto){
        System.out.println("conteudo foi consumido da fila: " + assunto);
    }
    
}