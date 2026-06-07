async function registerPatient() {
   
    const firstName = document.getElementById("firstName");
    const lastName = document.getElementById("lastName");
    const email = document.getElementById("email");
    const password = document.getElementById("password");
    const age = document.getElementById("age");
    const genderSelect = document.getElementById("gender");

    if (firstName.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter First Name before registering.";
        return;
    }  
    if (lastName.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Last Name before registering.";
        return;
    }  
    if (email.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Email before registering.";
        return;
    }  
    if (password.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Password before registering.";
        return;
    }  
    if (age.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Age before registering.";
        return;
    }  
    if (genderSelect.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please select a gender  before registering.";
        return;
    }  
     if (medicalHistory.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Medical History before registering.";
        return;
    }  
    if (existingCondition.value === "" ) { 
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Existing Conditions before registering.";
        return;
    }
    if (allergies.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Allergies before registering.";
        return;
    }
    if (medication.value === "" ) {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerText = "Please enter Current Medication before registering.";
        return;
    }
    


    const registerData = {

        firstName: document.getElementById("firstName").value,

        lastName: document.getElementById("lastName").value,

        email: document.getElementById("email").value,

        password: document.getElementById("password").value,

        age: document.getElementById("age").value,

        gender: document.getElementById("gender").value,

        medicalHistory: document.getElementById("medicalHistory").value,

        existingCondition: document.getElementById("existingCondition").value,
        

        allergies: document.getElementById("allergies").value,

        medication: document.getElementById("medication").value
    };

  alert(existingCondition);

        const response = await fetch(
                "http://localhost:8080/patient/register",
                {
                    method: "POST",
                    headers: {
                        "Content-Type":
                            "application/json"
                    },
                    body:
                        JSON.stringify(
                            registerData
                        )
                }
            );

        const messageDiv = document.getElementById("message");
          
        if (response.ok) {

            const data = await response.json();

            messageDiv.style.color = "green";

            messageDiv.innerText = data.message;            

            setTimeout(() => {

                window.location.href = "login.html";

            }, 2000);
            clearRegistrationForm();
        } else if (
            response.status === 409
        ) {

            messageDiv.style.color = "red";

            messageDiv.innerText = "Patient already registered. Redirecting to Login...";

            setTimeout(() => {

                window.location.href = "login.html";

            }, 3000);
            clearRegistrationForm();
        } else {

            messageDiv.style.color = "red";

            messageDiv.innerText = "Registration Failed";
            clearRegistrationForm();
        }


    
}

function clearRegistrationForm() {

    document.getElementById("firstName").value = "";
    document.getElementById("lastName").value = "";
    document.getElementById("email").value = "";
    document.getElementById("password").value = "";
    document.getElementById("age").value = "";
    document.getElementById("gender").value = "";
    document.getElementById("medicalHistory").value = "";
    document.getElementById("existingCondition").value = "";
    document.getElementById("allergies").value = "";
    document.getElementById("medication").value = "";

}