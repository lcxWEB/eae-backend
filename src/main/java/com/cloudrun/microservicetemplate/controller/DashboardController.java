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
    public ResponseEntity<List<Indicator>> getIcons() {
        List<Indicator> indicators = indicatorService.getStatusIndicators();
        return ResponseEntity.ok(indicators);
    }

    // ======================
    // Motor
    // ======================
    @GetMapping("/motor")
    public ResponseEntity<Motor> getMotorDetails() {
        Motor motor = motorService.getMotorDetails();
        return ResponseEntity.ok(motor);
    }

    @GetMapping("/motor/gear-ratio")
    public ResponseEntity<String> getGearRatio() {
        String gearRatio = motorService.getGearRatio();
        return ResponseEntity.ok(gearRatio);
    }

    @PostMapping("/motor/slider")
    public ResponseEntity<String> updateMotorSpeed(@RequestParam int speedSetting) {
        motorService.updateMotorSpeed(speedSetting);
        return ResponseEntity.ok("Motor speed updated");
    }

    // ======================
    // Gauges (Power & RPM)
    // ======================
    @GetMapping("/gauges/power")
    public ResponseEntity<Double> getPowerConsumption() {
        Double power = motorService.calculatePowerConsumption();
        return ResponseEntity.ok(power);
    }

    @GetMapping("/gauges/rpm")
    public ResponseEntity<Integer> getMotorRPM() {
        Integer rpm = motorService.getCurrentRPM();
        return ResponseEntity.ok(rpm);
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
