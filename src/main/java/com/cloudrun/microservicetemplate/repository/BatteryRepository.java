package com.cloudrun.microservicetemplate.repository;

import com.cloudrun.microservicetemplate.entities.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
}
