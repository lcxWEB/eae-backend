package com.cloudrun.microservicetemplate.service;

import com.cloudrun.microservicetemplate.entities.Battery;
import com.cloudrun.microservicetemplate.entities.Motor;
import com.cloudrun.microservicetemplate.repository.BatteryRepository;
import com.cloudrun.microservicetemplate.repository.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorService {

    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    private BatteryRepository batteryRepository;

    public Motor getMotorDetails() {
        return motorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Motor not found"));
    }

    public String getGearRatio() {
        Motor motor = motorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Motor not found"));
        return motor.getGearRatio();
    }

    public void updateMotorSpeed(int speedSetting) {
        Motor motor = motorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Motor not found"));
        Battery battery = batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));

        if (battery.getChargingState()) {
            motor.setRpm(0); // Motor disabled when charging
        } else {
            motor.setRpm(speedSetting * 200); // Example calculation
        }
        motor.setPowerConsumption((int) (motor.getRpm() * 1.2));
        motor.setSpeed(speedSetting);
        motorRepository.save(motor);
    }


}
