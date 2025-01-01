package com.cloudrun.microservicetemplate.service;

import com.cloudrun.microservicetemplate.entities.Battery;
import com.cloudrun.microservicetemplate.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    public Battery getBatteryDetails() {
        return batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));
    }

    public void updateChargingState(boolean charging) {
        Battery battery = batteryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Battery not found"));
        battery.setChargingState(charging);
        batteryRepository.save(battery);
    }
}
