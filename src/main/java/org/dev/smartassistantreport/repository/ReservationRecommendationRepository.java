package org.dev.smartassistantreport.repository;

import org.dev.smartassistantreport.model.ReservationRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRecommendationRepository extends JpaRepository<ReservationRecommendation, Long> {
}
