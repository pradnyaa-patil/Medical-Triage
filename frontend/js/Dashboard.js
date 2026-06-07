let dashboardData = {};

window.onload = function () {

    loadDashboard();
};

async function loadDashboard() {

    try {

        const response = await fetch(
            "http://localhost:8080/dashboard/cases",
            {
                headers: {
                    "Authorization":
                        "Bearer " +
                        localStorage.getItem("token")
                }
            }
        );

        dashboardData =
            await response.json();

        document.getElementById(
            "emergencyCount"
        ).innerText =
            "(" +
            dashboardData.emergency.length +
            ")";

        document.getElementById(
            "urgentCount"
        ).innerText =
            "(" +
            dashboardData.urgent.length +
            ")";

        document.getElementById(
            "severeCount"
        ).innerText =
            "(" +
            dashboardData.severe.length +
            ")";

        document.getElementById(
            "mediumCount"
        ).innerText =
            "(" +
            dashboardData.medium.length +
            ")";

        document.getElementById(
            "lowCount"
        ).innerText =
            "(" +
            dashboardData.low.length +
            ")";

        showSeverity("emergency");

    } catch (e) {

        alert(
            "Unable to load dashboard"
        );
    }
}

function showSeverity(severity) {

    const patients =
        dashboardData[severity];

    document.getElementById(
        "selectedSeverity"
    ).innerText =
        severity.toUpperCase() +
        " CASES";

    let html = "";

    if (
        !patients ||
        patients.length === 0
    ) {

        html =
            "<h5>No Cases Found</h5>";

    } else {

        patients.forEach(patient => {

            html +=

            `
            <div class="patient-card">

                <h5>
                    Patient:
                    ${patient.patientName || patient.patientId}
                </h5>

                <p>
                    <span class="label">
                        Severity:
                    </span>
                    ${patient.severity}
                </p>

                <p>
                    <span class="label">
                        Symptoms:
                    </span>
                    ${patient.symptoms}
                </p>

                <p>
                    <span class="label">
                        Reason:
                    </span>
                    ${patient.reason}
                </p>

            </div>
            `;
        });
    }

    document.getElementById(
        "patientList"
    ).innerHTML =
        html;
}