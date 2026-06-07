package com.triage.medicaltriage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeverityResult {

    private String severity;

    private String reason;

}
