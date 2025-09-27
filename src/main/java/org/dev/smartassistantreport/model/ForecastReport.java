package org.dev.smartassistantreport.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "forecast_report")
public class ForecastReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "service_items")
    private String serviceItems;
    
    @Column(name = "actual_09_2024")
    private BigDecimal actual092024;
    
    @Column(name = "actual_10_2024")
    private BigDecimal actual102024;
    
    @Column(name = "actual_11_2024")
    private BigDecimal actual112024;
    
    @Column(name = "actual_12_2024")
    private BigDecimal actual122024;
    
    @Column(name = "actual_01_2025")
    private BigDecimal actual012025;
    
    @Column(name = "actual_02_2025")
    private BigDecimal actual022025;
    
    @Column(name = "actual_03_2025")
    private BigDecimal actual032025;
    
    @Column(name = "actual_04_2025")
    private BigDecimal actual042025;
    
    @Column(name = "actual_05_2025")
    private BigDecimal actual052025;
    
    @Column(name = "actual_06_2025")
    private BigDecimal actual062025;
    
    @Column(name = "actual_07_2025")
    private BigDecimal actual072025;
    
    @Column(name = "actual_08_2025")
    private BigDecimal actual082025;
    
    @Column(name = "forecasted_09_2025")
    private BigDecimal forecasted092025;
    
    @Column(name = "forecasted_10_2025")
    private BigDecimal forecasted102025;
    
    @Column(name = "forecasted_11_2025")
    private BigDecimal forecasted112025;
    
    @Column(name = "forecasted_12_2025")
    private BigDecimal forecasted122025;
    
    @Column(name = "forecasted_01_2026")
    private BigDecimal forecasted012026;
    
    @Column(name = "forecasted_02_2026")
    private BigDecimal forecasted022026;
    
    @Column(name = "forecasted_03_2026")
    private BigDecimal forecasted032026;
    
    @Column(name = "forecasted_04_2026")
    private BigDecimal forecasted042026;
    
    @Column(name = "forecasted_05_2026")
    private BigDecimal forecasted052026;
    
    @Column(name = "forecasted_06_2026")
    private BigDecimal forecasted062026;
    
    @Column(name = "forecasted_07_2026")
    private BigDecimal forecasted072026;
    
    @Column(name = "forecasted_08_2026")
    private BigDecimal forecasted082026;
    
    private BigDecimal total;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}