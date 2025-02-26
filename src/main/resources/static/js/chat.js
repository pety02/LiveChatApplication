(() => {
    const sendMessageBtn = document.getElementById("sendMessageBtn");
    sendMessageBtn.addEventListener("click", sendMessage);
})()

let stompClient;

document.addEventListener("DOMContentLoaded", function () {
    fetch(`/api/chat/messages?userID=${usersMap[fromUser]}&friendID=${usersMap[toUser]}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(messages => {
            let messageList = document.getElementById("old-messages-list");

            messages.forEach(message => {
                let li = document.createElement("li");
                li.textContent = `${message.fromUser}: ${message.content}`;
                messageList.appendChild(li);
            });
        })
        .catch(error => console.error("Error fetching messages:", error));
});

function connect() {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

connect();

function sendMessage() {
    let messageContent = document.getElementById("message").value.trim();
    let now = new Date().getTime();

    if (messageContent && stompClient) {
        let message = {
            fromUser: fromUser,
            toUser: toUser,
            sentDate: now,
            content: messageContent
        };

        // Send message to WebSocket
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(message));
        showMessage(message);

        // Saves the message to the database
        let messageDTO = {
            fromUser: usersMap[fromUser],
            toUser: usersMap[toUser],
            sentDate: now,
            content: messageContent
        };
        $.ajax({
            url: "/api/send-message",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(messageDTO),
            success: function (response) {
                console.log("Message saved:", response);
            },
            error: function (xhr, status, error) {
                console.error("Error saving message:", xhr.responseText);
            }
        });

        // Clear input field
        document.getElementById("message").value = "";
    }
}

function showMessage(message) {
    let messageList = document.getElementById("old-messages-p");

    let li = document.createElement("li");
    li.textContent = `${message.fromUser}: ${message.content}`;

    messageList.appendChild(li);
}