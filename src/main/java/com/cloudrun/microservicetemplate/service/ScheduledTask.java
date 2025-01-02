package com.cloudrun.microservicetemplate.service;

import com.cloudrun.microservicetemplate.constants.Constant;
import com.cloudrun.microservicetemplate.entities.Battery;
import com.cloudrun.microservicetemplate.entities.Motor;
import com.cloudrun.microservicetemplate.repository.BatteryRepository;
import com.cloudrun.microservicetemplate.repository.MotorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled task to simulate the vehicle status
 * @author: lichunxia
 * @create: 2024/12/30 10:59 AM
 */
@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private MotorRepository motorRepository;
    @Autowired
    private BatteryRepository batteryRepository;

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void updateBatteryTemperature() {
        Battery battery = batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));
        Motor motor = motorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Motor not found"));

        Integer newTemperature = Math.min(50, battery.getTemperature() + (motor.getRpm() / 1000));
        battery.setTemperature(newTemperature);
        logger.info("updateBatteryTemperature to {}", newTemperature);
        batteryRepository.save(battery);
    }

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void updateMotorBattery() {
        Battery battery = batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));
        Motor motor = motorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Motor not found"));

        if (battery.getChargingState()) {
            battery.setPercentage(Math.min(100, battery.getPercentage() + 1)); // Simulate charging
            motor.setRpm(0);
            motor.setSpeed(0);
            motor.setPowerConsumption(Math.min(-1000, battery.getPercentage() * 10));
        } else if (motor.getRpm() >= 0) {
            battery.setPercentage(Math.max(0, battery.getPercentage() - 5)); // Simulate usage
            motor.setPowerConsumption(Math.max(0, (int) (motor.getRpm() * 1.2)));
        }
        batteryRepository.save(battery);
        motorRepository.save(motor);
        logger.info("updateMotorBattery, battery percentage: {}, power consumption: {}", battery.getPercentage(), motor.getPowerConsumption());
    }

}
