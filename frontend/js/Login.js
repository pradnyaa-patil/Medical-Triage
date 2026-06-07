async function login() {

    const loginData = {
       
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
        
    };

    try {
        
        const response = await fetch("http://localhost:8080/patient/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        });
        
        const data = await response.json();
       
        if(response.ok) {
            localStorage.setItem("token", data.token);
            localStorage.setItem("patientId", data.patientId);

            window.location.href = "chatbot.html";

        } else {
            document.getElementById("message").innerText = "Invalid Credentials";
            
        }

    } catch(error) {
        console.error(error);
    }
}