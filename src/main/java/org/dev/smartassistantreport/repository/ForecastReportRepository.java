package org.dev.smartassistantreport.repository;

import org.dev.smartassistantreport.model.ForecastReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastReportRepository extends JpaRepository<ForecastReport, Long> {
}
