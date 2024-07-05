package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor // queue definition
@Configuration
public class RabbitMQ {
    
    // definição do formato de exchange
    // - (Direct): envia dados para uma unica rota escolhida
    // - Topic: envia para a rota escolhida e para a rota padrão
    // - Fanout: envia para todas as rotas independente da escolha
    
    public static final String EXCHANGE_NAME = "direct-exchange";
    
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }
    
    // private final DirectExchange directExchange2;
    public static final String ROUTE_KEY = "nome.da.chave";
    public static final String QUEUE_NAME = "fila.um";
    
    // Criação da fila
    @Bean
    Queue queue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    // Associação da fila com a exchange
    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(ROUTE_KEY);
    }

    // Conversão de menssagem para JSON
    @Bean
    public MessageConverter jsonMessageConverter(){
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }
    
}
