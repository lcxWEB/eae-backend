package com.cloudrun.microservicetemplate.service;

import com.cloudrun.microservicetemplate.entities.Battery;
import com.cloudrun.microservicetemplate.entities.Motor;
import com.cloudrun.microservicetemplate.repository.BatteryRepository;
import com.cloudrun.microservicetemplate.repository.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;
    @Autowired
    private MotorRepository motorRepository;

    public Battery getBatteryDetails() {
        return batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));
    }

    public void updateChargingState(boolean charging) {
        Battery battery = batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));
        battery.setChargingState(charging);
        batteryRepository.save(battery);
        if (charging) {
            Motor motor = motorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Motor not found"));
            motor.setRpm(0);
            motor.setSpeed(0);
            motorRepository.save(motor);
        }
    }
}
