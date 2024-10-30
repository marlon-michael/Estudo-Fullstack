package com.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer{
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // abilita fila de menssagens em memoria em "/topics"
        registry.enableSimpleBroker("/topics");
        // adiciona os topicos em "/chat" e "/old-messages"
        registry.setApplicationDestinationPrefixes("/chat", "/old-messages");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        // endereço de conexão websocket
        registry.addEndpoint("/websocket-chat");
    }
}