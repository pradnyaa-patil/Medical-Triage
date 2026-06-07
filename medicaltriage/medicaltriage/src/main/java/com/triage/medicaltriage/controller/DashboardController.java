package com.triage.medicaltriage.controller;

import com.triage.medicaltriage.model.DashboardResponse;
import com.triage.medicaltriage.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    RestClient restClient = RestClient.create();
    @GetMapping("/cases")
    public DashboardResponse getCases() {
        return dashboardService.getDashboardCases();
    }

}
