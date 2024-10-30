package com.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.websocket.components.MessageHistory;
import com.websocket.models.MessageInput;
import com.websocket.models.MessageOutput;
import com.websocket.models.OldMessages;

@Controller
public class ChatController {
    @Autowired
    MessageHistory messageBroker;

    @MessageMapping("/new-message") // recebe novas menssagens no endereço "/new-message"
    @SendTo("/topics/chat") // redireciona menssagens para "/topics/chat"
    public MessageOutput chatMessage(MessageInput chat){
        // HtmlUtils.htmlEscape para transformar qualquer codigo em texto html
        MessageOutput message = new MessageOutput(chat.username() + " disse " + HtmlUtils.htmlEscape(chat.message()));
        messageBroker.addMessage(message);
        return message;
    }

    @MessageMapping("/old-messages") // recebe requisições do historico de menssagens no endereço "/old-messages"
    @SendTo("/topics/old-messages") // redireciona menssagens para "/topics/old-messages"
    public OldMessages chatLoad(){
        return new OldMessages(messageBroker.getMessages());
    } 
}
