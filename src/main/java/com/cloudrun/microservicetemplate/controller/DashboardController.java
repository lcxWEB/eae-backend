package com.cloudrun.microservicetemplate.controller;

import com.cloudrun.microservicetemplate.entities.Motor;
import com.cloudrun.microservicetemplate.service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    // @Autowired
    // private StatusIndicatorService statusIndicatorService;
    //
    @Autowired
    private MotorService motorService;

    // @Autowired
    // private BatteryService batteryService;

    // // ======================
    // // Status Indicators
    // // ======================
    // @GetMapping("/icons")
    // public ResponseEntity<List<Icon>> getIcons() {
    //     List<Icon> icons = statusIndicatorService.getStatusIndicators();
    //     return ResponseEntity.ok(icons);
    // }
    //
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
    //
    // @PostMapping("/motor/slider")
    // public ResponseEntity<String> updateMotorSpeed(@RequestParam int speedSetting) {
    //     motorService.updateMotorSpeed(speedSetting);
    //     return ResponseEntity.ok("Motor speed updated");
    // }
    //
    // // ======================
    // // Gauges (Power & RPM)
    // // ======================
    // @GetMapping("/gauges/power")
    // public ResponseEntity<Double> getPowerConsumption() {
    //     Double power = motorService.calculatePowerConsumption();
    //     return ResponseEntity.ok(power);
    // }
    //
    // @GetMapping("/gauges/rpm")
    // public ResponseEntity<Integer> getMotorRPM() {
    //     Integer rpm = motorService.getCurrentRPM();
    //     return ResponseEntity.ok(rpm);
    // }
    //
    // // ======================
    // // Battery
    // // ======================
    // @GetMapping("/battery")
    // public ResponseEntity<Battery> getBatteryDetails() {
    //     Battery battery = batteryService.getBatteryDetails();
    //     return ResponseEntity.ok(battery);
    // }
    //
    // @PostMapping("/battery/charging")
    // public ResponseEntity<String> updateChargingState(@RequestParam boolean charging) {
    //     batteryService.updateChargingState(charging);
    //     return ResponseEntity.ok("Charging state updated");
    // }
}
