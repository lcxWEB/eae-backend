package com.cloudrun.microservicetemplate.controller;

import com.cloudrun.microservicetemplate.entities.Battery;
import com.cloudrun.microservicetemplate.entities.Indicator;
import com.cloudrun.microservicetemplate.entities.Motor;
import com.cloudrun.microservicetemplate.service.BatteryService;
import com.cloudrun.microservicetemplate.service.MotorService;
import com.cloudrun.microservicetemplate.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private MotorService motorService;

    @Autowired
    private BatteryService batteryService;

    // ======================
    // Status Indicators
    // ======================
    @GetMapping("/indicators")
    public ResponseEntity<List<Indicator>> getIndicators() {
        List<Indicator> indicators = indicatorService.getStatusIndicators();
        return ResponseEntity.ok(indicators);
    }

    // ======================
    // Motor, Gauges (Power & RPM)
    // ======================
    @GetMapping("/motor")
    public ResponseEntity<Motor> getMotorDetails() {
        Motor motor = motorService.getMotorDetails();
        return ResponseEntity.ok(motor);
    }

    @PostMapping("/motor/update-speed")
    public ResponseEntity<String> updateMotorSpeed(@RequestParam int speed) {
        motorService.updateMotorSpeed(speed);
        return ResponseEntity.ok("Motor speed updated");
    }

    // ======================
    // Battery
    // ======================
    @GetMapping("/battery")
    public ResponseEntity<Battery> getBatteryDetails() {
        Battery battery = batteryService.getBatteryDetails();
        return ResponseEntity.ok(battery);
    }

    @PostMapping("/battery/charging")
    public ResponseEntity<String> updateChargingState(@RequestParam boolean charging) {
        batteryService.updateChargingState(charging);
        return ResponseEntity.ok("Charging state updated");
    }

}
