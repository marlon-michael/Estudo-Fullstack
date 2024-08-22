package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProducerConfig {
  // definição do formato de exchange
  // - (Direct): envia dados para uma unica rota escolhida
  // - Topic: envia para a rota escolhida e para a rota padrão
  // - Fanout: envia para todas as rotas independente da escolha
  
  public static final String EXCHANGE_NAME = "exchange-direct"; // nome da exchange
  public static final String ROUTE_KEY = "rota.menssagem"; // nome da rota
  String QUEUE_NAME = "fila.um"; // nome da fila
  
  @Bean
  DirectExchange directExchange (){
    return new DirectExchange(EXCHANGE_NAME);
  }
  
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
}
