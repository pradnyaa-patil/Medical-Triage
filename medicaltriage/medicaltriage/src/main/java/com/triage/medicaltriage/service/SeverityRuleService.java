package com.triage.medicaltriage.service;

import com.triage.medicaltriage.model.SeverityResult;

public interface SeverityRuleService {
    SeverityResult determineSeverity(String text, int age, String existingCondition);
}
