package org.dev.smartassistantreport.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rightsizing_recommendations")
public class RightsizingRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String instanceName;
    private String instanceId;
    private String instanceState;
    private LocalDateTime launchDate;
    private String region;
    private String operatingSystem;
    private String tags;
    private String autoScalingGroupName;
    private BigDecimal totalHours;  // Changed from Integer to BigDecimal
    private String instanceType;
    private String accountName;
    private BigDecimal cpuAvg;
    private BigDecimal memoryAvg;
    private BigDecimal diskAvg;
    private BigDecimal cpuMax;
    private BigDecimal memoryMax;
    private BigDecimal diskMax;
    private BigDecimal netOutAvg;
    private String status;
    private BigDecimal currentCost;
    private BigDecimal projectedCost;
    private BigDecimal projectedSavingsByCost;
    private BigDecimal currentPrice;
    private BigDecimal projectedPrice;
    private BigDecimal projectedSavings;
    private Boolean terminateRecommendation;
    private String bestFitInstanceAssetServiceTypeName;
    private String perspectiveName1;
    private String perspectiveGroupName1;
    private String perspectiveName2;
    private String perspectiveGroupName2;
    private String inFamilyRecommendation;
    private BigDecimal inFamilyRecommendationProjectedCost;
    private BigDecimal inFamilyRecommendationProjectedPrice;
    private LocalDateTime createdAt;
}