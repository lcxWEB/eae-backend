package com.cloudrun.microservicetemplate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Motor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rpm;
    private Double powerConsumption;

    private Integer gearRatioNumerator; // Numerator of the gear ratio
    private Integer gearRatioDenominator; // Denominator of the gear ratio

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public Double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(Double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public Integer getGearRatioNumerator() {
        return gearRatioNumerator;
    }

    public void setGearRatioNumerator(Integer gearRatioNumerator) {
        this.gearRatioNumerator = gearRatioNumerator;
    }

    public Integer getGearRatioDenominator() {
        return gearRatioDenominator;
    }

    public void setGearRatioDenominator(Integer gearRatioDenominator) {
        this.gearRatioDenominator = gearRatioDenominator;
    }

    public String getGearRatio() {
        return gearRatioNumerator + "/" + gearRatioDenominator;
    }
}
