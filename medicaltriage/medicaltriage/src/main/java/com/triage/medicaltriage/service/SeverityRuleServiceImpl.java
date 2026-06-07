package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.SeverityResult;
import org.springframework.stereotype.Service;

@Service
public class SeverityRuleServiceImpl
        implements SeverityRuleService {

    @Override
    public SeverityResult determineSeverity(String text, int age, String existingCondition) {
        text = text.toLowerCase();

        /*
         * ==========================
         * EMERGENCY
         * ==========================
         */

        if(age > 65 && text.contains("chest pain")) {

            return new SeverityResult(
                    "EMERGENCY",
                    "Chest pain at the age of "+age);
        }
        if(existingCondition != null && existingCondition.contains("heart disease") && text.contains("chest pain")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "\"Chest pain in a patient with heart disease.");
        }

        if(existingCondition != null && existingCondition.contains("copd") && text.contains("difficulty breathing")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Breathing difficulty in COPD patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("epilepsy") && text.contains("seizure")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Seizure detected in epilepsy patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("stroke") && text.contains("weakness")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Possible recurrent stroke symptoms."
            );
        }

        if(existingCondition != null && existingCondition.contains("kidney disease") && text.contains("confusion")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Confusion may indicate severe kidney complications."
            );
        }

        if(existingCondition != null && existingCondition.contains("coronary artery disease") && text.contains("chest pain")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Possible cardiac emergency."
            );
        }

        if (text.contains("chest pain") && text.contains("breathing")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Chest pain with breathing difficulty may indicate a serious cardiac or respiratory emergency."
            );
        }

        if (text.contains("unconscious") || text.contains("not breathing") || text.contains("stopped breathing")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Patient may be experiencing life-threatening respiratory failure."
            );
        }

        if (text.contains("seizure") || text.contains("convulsion")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Seizure requires immediate medical attention."
            );
        }

        if (text.contains("stroke")
                || text.contains("face drooping")
                || text.contains("cannot speak")) {

            return new SeverityResult(
                    "EMERGENCY",
                    "Possible stroke symptoms detected."
            );
        }

        if (text.contains("severe bleeding")
                || text.contains("blood loss")) {

            return new SeverityResult(
                    "EMERGENCY",
                    "Severe bleeding may be life-threatening."
            );
        }

        if(existingCondition != null && existingCondition.contains("asthma") && text.contains("shortness of breath")) {
            return new SeverityResult(
                    "EMERGENCY",
                    "Shortness of breath in a patient with asthma."
            );
        }

        /*
         * ==========================
         * URGENT
         * ==========================
         */

        if(existingCondition != null && existingCondition.contains("diabetes") && text.contains("foot infection")) {
            return new SeverityResult(
                    "URGENT",
                    "Foot infection for a diabetic patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("diabetes") && text.contains("blurred vision")) {
            return new SeverityResult(
                    "URGENT",
                    "Blurred vision may indicate uncontrolled diabetes."
            );
        }

        if(existingCondition != null && existingCondition.contains("hypertension") && text.contains("severe headache")) {
            return new SeverityResult(
                    "URGENT",
                    "Severe headache in hypertensive patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("heart disease") && text.contains("palpitations")) {
            return new SeverityResult(
                    "URGENT",
                    "Palpitations in heart disease patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("pregnancy") && text.contains("bleeding")) {
            return new SeverityResult(
                    "URGENT",
                    "Bleeding during pregnancy."
            );
        }

        if(existingCondition != null && existingCondition.contains("asthma") && text.contains("wheezing")) {
            return new SeverityResult(
                    "URGENT",
                    "Worsening asthma symptoms."
            );
        }

        if (text.contains("chest pain")) {
            return new SeverityResult(
                    "URGENT",
                    "Chest pain requires urgent medical evaluation."
            );
        }

        if (text.contains("shortness of breath")) {
            return new SeverityResult(
                    "URGENT",
                    "Breathing difficulty requires urgent attention."
            );
        }

        if (text.contains("high fever") || text.contains("104")) {
            return new SeverityResult(
                    "URGENT",
                    "High fever may indicate a serious infection."
            );
        }

        if (text.contains("vomiting blood")) {
            return new SeverityResult(
                    "URGENT",
                    "Vomiting blood requires immediate evaluation."
            );
        }

        if (text.contains("severe abdominal pain")) {
            return new SeverityResult(
                    "URGENT",
                    "Severe abdominal pain may indicate a surgical emergency."
            );
        }

        /*
         * ==========================
         * SEVERE
         * ==========================
         */
        if(existingCondition != null && existingCondition.contains("diabetes") && text.contains("persistent fever")) {
            return new SeverityResult(
                    "SEVERE",
                    "Persistent fever in diabetic patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("hypertension") && text.contains("dizziness")) {
            return new SeverityResult(
                    "SEVERE",
                    "Dizziness in patient with hypertension."
            );
        }

        if(existingCondition != null && existingCondition.contains("migraine") && text.contains("severe headache")) {
            return new SeverityResult(
                    "SEVERE",
                    "Severe migraine symptoms."
            );
        }

        if(existingCondition != null && existingCondition.contains("arthritis") && text.contains("joint swelling")) {
            return new SeverityResult(
                    "SEVERE",
                    "Joint swelling in arthritis patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("copd") && text.contains("persistent cough")) {
            return new SeverityResult(
                    "SEVERE",
                    "Persistent cough in COPD patient."
            );
        }

        if(existingCondition != null && existingCondition.contains("kidney disease") && text.contains("leg swelling")) {
            return new SeverityResult(
                    "SEVERE",
                    "Swelling may indicate worsening kidney disease."
            );
        }

        if (text.contains("persistent fever")) {
            return new SeverityResult(
                    "SEVERE",
                    "Persistent fever should be evaluated by a physician."
            );
        }

        if (text.contains("dehydration")) {
            return new SeverityResult(
                    "SEVERE",
                    "Signs of dehydration require medical assessment."
            );
        }

        if (text.contains("severe headache")) {
            return new SeverityResult(
                    "SEVERE",
                    "Severe headache requires further evaluation."
            );
        }

        if (text.contains("continuous vomiting")) {
            return new SeverityResult(
                    "SEVERE",
                    "Persistent vomiting may require treatment."
            );
        }

        if (text.contains("rash") && text.contains("fever")) {
            return new SeverityResult(
                    "SEVERE",
                    "Rash with fever may indicate significant infection."
            );
        }

        /*
         * ==========================
         * MEDIUM
         * ==========================
         */

        if(existingCondition != null && existingCondition.contains("allergy") && text.contains("rash")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Allergic rash detected."
            );
        }

        if(existingCondition != null && existingCondition.contains("sinusitis") && text.contains("headache")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Headache associated with sinus symptoms."
            );
        }
        if(existingCondition != null && existingCondition.contains("gastritis") && text.contains("stomach pain")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Stomach pain may be related to gastritis."
            );
        }
        if(existingCondition != null && existingCondition.contains("anemia") && text.contains("fatigue")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Fatigue may be related to anemia."
            );
        }

        if(existingCondition != null && existingCondition.contains("thyroid") && text.contains("fatigue")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Fatigue may be related to thyroid condition."
            );
        }

        if (text.contains("fever")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Possible infection."
            );
        }

        if (text.contains("cough")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Respiratory symptoms may require consultation."
            );
        }

        if (text.contains("sore throat")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Possible throat infection."
            );
        }

        if (text.contains("body ache") || text.contains("body pain")) {
            return new SeverityResult(
                    "MEDIUM",
                    "General illness symptoms detected."
            );
        }

        if (text.contains("ear pain")) {
            return new SeverityResult(
                    "MEDIUM",
                    "Possible ear infection."
            );
        }

        /*
         * ==========================
         * LOW
         * ==========================
         */

        if (text.contains("mild headache")) {
            return new SeverityResult(
                    "LOW",
                    "Mild headache can often be monitored at home."
            );
        }

        if (text.contains("runny nose")) {
            return new SeverityResult(
                    "LOW",
                    "Likely mild upper respiratory symptoms."
            );
        }

        if (text.contains("sneezing")) {
            return new SeverityResult(
                    "LOW",
                    "Possible allergy or common cold."
            );
        }

        if (text.contains("mild cough")) {
            return new SeverityResult(
                    "LOW",
                    "Mild cough can often be managed at home."
            );
        }

        if(existingCondition != null && existingCondition.contains("seasonal allergy") && text.contains("sneezing")) {
            return new SeverityResult(
                    "LOW",
                    "Symptoms consistent with seasonal allergies."
            );
        }

        if(existingCondition != null && existingCondition.contains("allergy") && text.contains("runny nose")) {
            return new SeverityResult(
                    "LOW",
                    "Mild allergy symptoms."
            );
        }

        if(existingCondition != null && existingCondition.contains("migraine") && text.contains("mild headache")) {
            return new SeverityResult(
                    "LOW",
                    "Mild headache reported."
            );
        }

        if(existingCondition != null && existingCondition.contains("eczema") && text.contains("itching")) {
            return new SeverityResult(
                    "LOW",
                    "Mild skin irritation."
            );
        }

        if(existingCondition != null && existingCondition.contains("sinusitis") && text.contains("nasal congestion")) {
            return new SeverityResult(
                    "LOW",
                    "Mild sinus congestion symptoms."
            );
        }


        return new SeverityResult(
                "LOW",
                "No major warning signs detected."
        );
    }
}