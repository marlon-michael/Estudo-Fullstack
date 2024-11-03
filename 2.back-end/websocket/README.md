# Projeto Websocket

- [Dependencias](#dependencias)
- [Configurando Websocket](#configurações-do-websocket)
- [Modelos de entrada e saída de menssagens](#modelos)
- [Componente de histórico de menssagens](#componente-de-historico-de-mensssagens)
- [Controllers e endpoints de comunicação](#controller-e-endpoints-de-comunicação)
- [Consumindo menssagens do websocket em com StompJS](#consumindo-menssagens-do-websocket-em-js-com-stompjs)

---

- ### Websocket
    Websocket é um protocolo capaz de trocar menssagens em tempo real entre servidor e multiplos clientes conectados.

- ### Dependencias
    - pom.xml
    ```xml
    <!-- Websocket -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    <!-- Spring Web - Servir frontend da pasta "/resources/static" -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

- ### Configurações do Websocket
    - /config/WebsocketConfiguration.java
    ```java
    import org.springframework.context.annotation.Configuration;
    import org.springframework.messaging.simp.config.MessageBrokerRegistry;
    import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
    import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
    import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

    @Configuration
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
    ```

- ### Modelos
    - /models/MessageInput.java
    ```java
    public record MessageInput(String username, String message){}
    ```

    - /models/MessageOutput.java
    ```java
    public record MessageOutput(String messages){}
    ```

    - /models/OldMessages.java
    ```java
    public record OldMessages(List<MessageOutput> messages){}
    ```

- ### Componente de historico de mensssagens

    - /components/MessageHistory.java
    ```java
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

        public List<MessageOutput> addMessage(MessageOutput message){
            messages.add(message);
            return messages;
        }
    }

    ```

- ### Controller e Endpoints de comunicação
    - /controller/ChatController.java
    ```java
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
    public class ChatController{
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
    ```

- ### Consumindo menssagens do Websocket em JS com StompJS
    - /resources/static/app.js
    ```javascript
    // Define conexão com o websocket no backend no endpoint "/websocket-chat"
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://' + window.location.host + '/websocket-chat'
    });

    // executa ao conectar no websocket
    stompClient.onConnect = (frame) => {
        console.log('Connected: ' + frame);
        // se inscreve no topico de menssagens anteriores
        let oldMessagensSubscription = stompClient.subscribe("/topics/old-messages", (response) => {
            // após recuperar as menssagens se desinscreve do topico de menssagens anteriores
            oldMessagensSubscription.unsubscribe();
                // se inscreve para receber menssagens em tempo real
            stompClient.subscribe("/topics/chat", (message) => {
                updateLiveChat(JSON.parse(message.body).content);
            });
            // converte a responta json em um array de menssagens
            let messages = Object.values(JSON.parse(response.body).messages);
            // limpa div chat antiga
            clearLiveChat();
            messages.map((message) => {
                // adiciona cada menssagem do historico a div chat
                updateLiveChat(message.content);
            });
        });
        // faz requsição para receber menssagens antigas
        stompClient.publish({ destination: "/chat/old-messages" });
    }

    // log de erros com websocket
    stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };

    stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };

    // mostra se o frontend está conectado
    function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        }
        else {
            $("#conversation").hide();
        }
    }


    // ativa conexão com websocket
    function connect() {
        stompClient.activate();
        setConnected(true);
    }

    // desativa conexão com websocket
    function disconnect() {
        stompClient.deactivate();
        setConnected(false);
    }

    // Envia menssagem ao websocket
    function sendMessage() {
        if ($("#user").val().length < 1 || $("#message").val().length < 1) return;
        stompClient.publish({
            destination: "/chat/new-message",
            body: JSON.stringify({ 'username': $("#user").val(), 'message': $("#message").val() })
        });
        $("#message").val("");
    }

    // limpa div chat antiga
    function clearLiveChat() {
        $("#livechat").empty();
    }

    // adiciona menssagens no inicio da div chat
    function updateLiveChat(message) {
        $("#livechat").prepend("<tr><td>" + message + "</td></tr>");
    }

    // define evento por pressionamento da tecla
    function keyPressedHandler() {
        document.addEventListener('keydown', (event) => {
            // ENTER envia menssagem
            if (event.key === 'Enter') sendMessage();
        })
    };

    // define eventos no frontend
    $(function () {
        keyPressedHandler();
        $("form").on('submit', (e) => e.preventDefault());
        $("#connect").click(() => connect());
        $("#disconnect").click(() => disconnect());
        $("#send").click(() => sendMessage());
    });
    ```