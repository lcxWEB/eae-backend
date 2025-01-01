package com.cloudrun.microservicetemplate.repository;

import com.cloudrun.microservicetemplate.entities.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
}
