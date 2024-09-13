package com.rabbitmq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    public static final String QUEUE_NAME = "fila.um"; // nome da fila

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO); // define como lidar com a confirmação de recebimento das menssagens
        factory.setPrefetchCount(5); // número de menssagens para casa consumidor
        factory.setConsumersPerQueue(2); // número de consumidores por fila
        factory.setGlobalQos(true); // define a configuração para todo o sistema
        return factory;
    }
}
