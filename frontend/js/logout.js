async function logout() {  
    localStorage.removeItem("token");
    localStorage.removeItem("patientId");
    localStorage.removeItem("firstName");
    window.location.href = "login.html";
}