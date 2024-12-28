package com.cloudrun.microservicetemplate.repository;

import com.cloudrun.microservicetemplate.entities.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long> {
}
