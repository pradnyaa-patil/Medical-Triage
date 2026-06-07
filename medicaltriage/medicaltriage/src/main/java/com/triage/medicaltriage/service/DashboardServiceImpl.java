package com.triage.medicaltriage.service;

import com.triage.medicaltriage.entity.PatientCurrentStatusEntity;
import com.triage.medicaltriage.entity.PatientEntity;
import com.triage.medicaltriage.model.DashboardCase;
import com.triage.medicaltriage.model.DashboardResponse;
import com.triage.medicaltriage.repository.PatientCurrentStatusRepository;
import com.triage.medicaltriage.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    private final PatientCurrentStatusRepository currentStatusRepository;

    private final PatientRepository patientRepository;

    public DashboardServiceImpl(PatientCurrentStatusRepository currentStatusRepository, PatientRepository patientRepository) {
        this.currentStatusRepository = currentStatusRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public DashboardResponse getDashboardCases() {

        DashboardResponse response = new DashboardResponse();

        response.setEmergency(buildCases("EMERGENCY"));
        response.setUrgent(buildCases("URGENT"));
        response.setSevere(buildCases("SEVERE"));
        response.setMedium(buildCases("MEDIUM"));
        response.setLow(buildCases("LOW"));

        return response;
    }

    private List<DashboardCase>buildCases(String severity) {

        List<PatientCurrentStatusEntity> statuses = currentStatusRepository.findBySeverity(severity);

        return statuses.stream()
                .map(status -> {

                    DashboardCase dashboardCase = new DashboardCase();

                    dashboardCase.setPatientId(status.getPatientId());
                    dashboardCase.setSeverity(status.getSeverity());
                    dashboardCase.setSymptoms(status.getSymptomSummary());
                    dashboardCase.setReason(status.getReason());

                    PatientEntity patient = patientRepository.findById(status.getPatientId()).orElse(null);

                    if (patient != null) {
                        dashboardCase.setPatientName(patient.getFirstName()+ " "+ patient.getLastName());
                        dashboardCase.setAge(patient.getAge());
                        dashboardCase.setGender(patient.getGender());
                    }

                    return dashboardCase;

                })
                .toList();
    }
}