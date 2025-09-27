package org.dev.smartassistantreport.repository;

import org.dev.smartassistantreport.model.CostAnomaly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostAnomalyRepository extends JpaRepository<CostAnomaly, Long> {
}
