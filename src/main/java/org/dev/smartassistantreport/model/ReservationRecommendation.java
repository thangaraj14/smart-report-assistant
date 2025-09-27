package org.dev.smartassistantreport.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservation_recommendations")
public class ReservationRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String linkedAccountId;
    private String accountName;
    private String instanceRegion;
    private String service;
    private String instanceType;
    private String usageType;
    private String platform;
    private BigDecimal currentCoverage;
    private BigDecimal newCoverage;
    private Integer recommendedPurchases;
    private BigDecimal upfrontCost;
    private BigDecimal monthlyCost;
    private BigDecimal estimatedMonthlySavings;
    private BigDecimal estimatedYearlySavings;
    private LocalDateTime createdAt;
}
