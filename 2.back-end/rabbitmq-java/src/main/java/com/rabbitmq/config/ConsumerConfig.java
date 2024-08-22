package com.rabbitmq.config;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ConsumerConfig {
  public static final String QUEUE_NAME = "fila.um"; // nome da fila
}
