window.onload = async function() {

    try {

        const token = localStorage.getItem("token");

       
        const response = await fetch("http://localhost:8080/dashboard/cases",
        {
            headers: {

                "Authorization": "Bearer " + token
            }
        });


        const data = await response.json();

        const container = document.getElementById("casesContainer");


 const severityLevels = [
    "emergency",
    "urgent",
    "severe",
    "medium",
    "low"
];

severityLevels.forEach(level => {

    const patients = data[level];

    patients.forEach(patientCase => {

        const div = document.createElement("div");

        div.classList.add("severity-card");

        div.classList.add(level);

        div.innerHTML = `
            <h4>${patientCase.patientName}</h4>
            <p>Severity: ${patientCase.severity}</p>
            <p>Age: ${patientCase.age}</p>
            <p>Gender: ${patientCase.gender}</p>
            <p>Symptoms: ${patientCase.symptoms}</p>
            <p>Reason: ${patientCase.reason}</p>
        `;

        container.appendChild(div);
    });
});

    } catch(error) {
        console.error(error);
    }
}