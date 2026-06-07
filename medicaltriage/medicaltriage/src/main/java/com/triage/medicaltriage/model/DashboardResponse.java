package com.triage.medicaltriage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private List<DashboardCase> emergency;
    private List<DashboardCase> urgent;
    private List<DashboardCase> severe;
    private List<DashboardCase> medium;
    private List<DashboardCase> low;
}
