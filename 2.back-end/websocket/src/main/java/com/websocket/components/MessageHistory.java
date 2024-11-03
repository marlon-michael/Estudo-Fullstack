package com.websocket.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.websocket.models.MessageOutput;

// In Memory message history
@Component
public class MessageHistory {
    private List<MessageOutput> messages = new ArrayList<>();
    
    public MessageHistory(){
        messages.add(new MessageOutput("Bem vindo"));
    }

    public List<MessageOutput> getMessages(){
        return messages;
    }

    public void addMessage(MessageOutput message){
        messages.add(message);
    }
}
