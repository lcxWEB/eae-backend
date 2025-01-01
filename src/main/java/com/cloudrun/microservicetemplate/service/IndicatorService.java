package com.cloudrun.microservicetemplate.service;

import com.cloudrun.microservicetemplate.entities.Battery;
import com.cloudrun.microservicetemplate.entities.Indicator;
import com.cloudrun.microservicetemplate.entities.Motor;
import com.cloudrun.microservicetemplate.repository.BatteryRepository;
import com.cloudrun.microservicetemplate.repository.IndicatorRepository;
import com.cloudrun.microservicetemplate.repository.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lichunxia
 * @create: 2024/12/27 12:03 PM
 */
@Service
public class IndicatorService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    private BatteryRepository batteryRepository;

    public List<Indicator> getStatusIndicators() {
        List<Indicator> indicators = indicatorRepository.findAll();

        // Update dynamic indicators (e.g., Motor and Battery Low)
        Motor motor = motorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Motor not found"));
        Battery battery = batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));

        indicators.stream()
                .filter(indicator -> indicator.getName().equals("motor"))
                .findFirst()
                .ifPresent(indicator -> indicator.setActiveState(motor.getRpm() > 1000));

        indicators.stream()
                .filter(indicator -> indicator.getName().equals("battery_low"))
                .findFirst()
                .ifPresent(indicator -> indicator.setActiveState(battery.getPercentage() < 20));

        return indicators;
    }

}

