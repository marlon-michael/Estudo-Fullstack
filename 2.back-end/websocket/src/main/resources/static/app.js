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
            updateLiveChat(JSON.parse(message.body).content)
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

// define eventos do frontend
$(function () {
    keyPressedHandler();
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => sendMessage());
});