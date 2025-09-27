-- Cost Anomalies Table
CREATE TABLE IF NOT EXISTS cost_anomalies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_date TIMESTAMP NOT NULL,
    duration VARCHAR(50),
    account_id VARCHAR(15) NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    service VARCHAR(100) NOT NULL,
    region VARCHAR(50) NOT NULL,
    marketplace BOOLEAN NOT NULL,
    cost_impact_type VARCHAR(50) NOT NULL,
    cost_impact DECIMAL(12,2) NOT NULL,
    cost_impact_percentage DECIMAL(12,2) NOT NULL,
    cost DECIMAL(12,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    feedback VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Forecast Report Table
CREATE TABLE IF NOT EXISTS forecast_report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_items VARCHAR(255) NOT NULL,
    actual_09_2024 DECIMAL(15,2),
    actual_10_2024 DECIMAL(15,2),
    actual_11_2024 DECIMAL(15,2),
    actual_12_2024 DECIMAL(15,2),
    actual_01_2025 DECIMAL(15,2),
    actual_02_2025 DECIMAL(15,2),
    actual_03_2025 DECIMAL(15,2),
    actual_04_2025 DECIMAL(15,2),
    actual_05_2025 DECIMAL(15,2),
    actual_06_2025 DECIMAL(15,2),
    actual_07_2025 DECIMAL(15,2),
    actual_08_2025 DECIMAL(15,2),
    forecasted_09_2025 DECIMAL(15,2),
    forecasted_10_2025 DECIMAL(15,2),
    forecasted_11_2025 DECIMAL(15,2),
    forecasted_12_2025 DECIMAL(15,2),
    forecasted_01_2026 DECIMAL(15,2),
    forecasted_02_2026 DECIMAL(15,2),
    forecasted_03_2026 DECIMAL(15,2),
    forecasted_04_2026 DECIMAL(15,2),
    forecasted_05_2026 DECIMAL(15,2),
    forecasted_06_2026 DECIMAL(15,2),
    forecasted_07_2026 DECIMAL(15,2),
    forecasted_08_2026 DECIMAL(15,2),
    total DECIMAL(15,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Reservation Recommendations Table
CREATE TABLE IF NOT EXISTS reservation_recommendations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    linked_account_id VARCHAR(15) NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    instance_region VARCHAR(50) NOT NULL,
    service VARCHAR(100) NOT NULL,
    instance_type VARCHAR(100),
    usage_type VARCHAR(100),
    platform VARCHAR(100),
    current_coverage DECIMAL(5,2) NOT NULL,
    new_coverage DECIMAL(5,2) NOT NULL,
    recommended_purchases INTEGER NOT NULL,
    upfront_cost DECIMAL(15,2) NOT NULL,
    monthly_cost DECIMAL(15,2) NOT NULL,
    estimated_monthly_savings DECIMAL(15,2) NOT NULL,
    estimated_yearly_savings DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Rightsizing Recommendations Table
CREATE TABLE IF NOT EXISTS rightsizing_recommendations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    instance_name VARCHAR(255) NOT NULL,
    instance_id VARCHAR(50) NOT NULL,
    instance_state VARCHAR(50) NOT NULL,
    launch_date TIMESTAMP NOT NULL,
    region VARCHAR(50) NOT NULL,
    operating_system VARCHAR(50) NOT NULL,
    tags TEXT,
    auto_scaling_group_name VARCHAR(255),
    total_hours DECIMAL(10,4) NOT NULL,
    instance_type VARCHAR(50) NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    cpu_avg DECIMAL(8,4),
    memory_avg DECIMAL(8,4),
    disk_avg DECIMAL(8,4),
    cpu_max DECIMAL(8,4),
    memory_max DECIMAL(8,4),
    disk_max DECIMAL(8,4),
    net_out_avg DECIMAL(8,4),
    status VARCHAR(50) NOT NULL,
    current_cost DECIMAL(15,4),  -- Made nullable
    projected_cost DECIMAL(15,4),  -- Made nullable
    projected_savings_by_cost DECIMAL(15,4),  -- Made nullable
    current_price DECIMAL(15,4),  -- Made nullable
    projected_price DECIMAL(15,4),  -- Made nullable
    projected_savings DECIMAL(15,4),  -- Made nullable
    terminate_recommendation BOOLEAN NOT NULL,
    best_fit_instance_asset_service_type_name VARCHAR(100),
    perspective_name1 VARCHAR(255),
    perspective_group_name1 VARCHAR(255),
    perspective_name2 VARCHAR(255),
    perspective_group_name2 VARCHAR(255),
    in_family_recommendation VARCHAR(100),
    in_family_recommendation_projected_cost DECIMAL(15,4),
    in_family_recommendation_projected_price DECIMAL(15,4),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Add indexes for better query performance
CREATE INDEX idx_cost_anomalies_account_id ON cost_anomalies(account_id);
CREATE INDEX idx_cost_anomalies_service ON cost_anomalies(service);
CREATE INDEX idx_cost_anomalies_status ON cost_anomalies(status);

CREATE INDEX idx_forecast_report_service_items ON forecast_report(service_items);

CREATE INDEX idx_reservation_recommendations_account ON reservation_recommendations(linked_account_id);
CREATE INDEX idx_reservation_recommendations_service ON reservation_recommendations(service);

CREATE INDEX idx_rightsizing_recommendations_instance ON rightsizing_recommendations(instance_id);
CREATE INDEX idx_rightsizing_recommendations_account ON rightsizing_recommendations(account_name);
CREATE INDEX idx_rightsizing_recommendations_status ON rightsizing_recommendations(status);