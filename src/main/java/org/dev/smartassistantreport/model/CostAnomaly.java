package org.dev.smartassistantreport.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cost_anomalies")
public class CostAnomaly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime startDate;
    private String duration;
    private String accountId;
    private String accountName;
    private String service;
    private String region;
    private Boolean marketplace;
    private String costImpactType;
    private BigDecimal costImpact;
    private BigDecimal costImpactPercentage;
    private BigDecimal cost;
    private String status;
    private String feedback;
    private LocalDateTime createdAt;
}
