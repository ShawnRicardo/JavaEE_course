package com.example.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class ApiController {

    @Autowired
    private ApiMonitoringAspect apiMonitoringAspect;

    @GetMapping("/api1")
    @ApiMonitor
    public String api1() {
        // Your API logic here
        return "API 1 response";
    }

    @GetMapping("/api2")
    @ApiMonitor
    public String api2() {
        // Your API logic here
        return "API 2 response";
    }

    @GetMapping("/apiStats")
    public Map<String, ApiStats> getApiStats() {
        return apiMonitoringAspect.getApiStatsMap();
    }
}