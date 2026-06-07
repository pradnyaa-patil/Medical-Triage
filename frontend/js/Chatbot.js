window.onload = function () {

    const token = localStorage.getItem("token");

    if (!token) {
        window.location.href = "login.html";
        return;
    }
};




async function sendMessage() {

    const input = document.getElementById("messageInput");
    const patientId = localStorage.getItem("patientId");
    const token = localStorage.getItem("token");



    const message = input.value;
   
    if(message.trim() === "") return;

    addMessage(message, "user-message");

    input.value = "";

    const chatReq = {
        message: message,
        patientId:  localStorage.getItem("patientId")
    };

    try {

    const response = await fetch("http://localhost:8080/chat/message",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(chatReq)
        }
    );

    const data = await response.json();

    addMessage(data.reply, "bot-message");

    /*if(data.severity) {

        addMessage(
            "Severity: " + data.severity,
            "bot-message"
        );
    }*/

} catch(error) {

    console.error(error);
}
}

function addMessage(message, className) {

    const chatBox = document.getElementById("chatBox");

    const div = document.createElement("div");

    div.classList.add("message" );

    div.classList.add(className);

    div.innerText = message;

    chatBox.appendChild(div);

    chatBox.scrollTop = chatBox.scrollHeight;
}