package org.dev.smartassistantreport.service;

import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.smartassistantreport.model.*;
import org.dev.smartassistantreport.repository.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataImportService {
    private final CostAnomalyRepository costAnomalyRepository;
    private final ForecastReportRepository forecastReportRepository;
    private final ReservationRecommendationRepository reservationRecommendationRepository;
    private final RightsizingRecommendationRepository rightsizingRecommendationRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @PostConstruct
    @Transactional
    public void init() {
        log.info("Starting data import during application startup...");
        try {
            importCostAnomalies();
            importForecastReport();
            importReservationRecommendations();
            importRightsizingRecommendations();
            log.info("Data import completed successfully during startup");
        } catch (Exception e) {
            log.error("Error during startup data import: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to import data during startup", e);
        }
    }

    private BigDecimal toBigDecimal(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        return new BigDecimal(value.replace("\"", "").replace(",", ""));
    }

    private void importCostAnomalies() {
        String resourcePath = "source/costAnomalies.csv";
        log.info("Importing cost anomalies from {}", resourcePath);
        
        List<CostAnomaly> anomalies = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(resourcePath).getInputStream()))) {
            String[] headers = reader.readNext(); // Skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                CostAnomaly anomaly = new CostAnomaly();
                anomaly.setStartDate(LocalDateTime.parse(line[0], DATE_FORMATTER));
                anomaly.setDuration(line[1]);
                anomaly.setAccountId(line[2]);
                anomaly.setAccountName(line[3]);
                anomaly.setService(line[4]);
                anomaly.setRegion(line[5]);
                anomaly.setMarketplace(Boolean.parseBoolean(line[6]));
                anomaly.setCostImpactType(line[7]);
                anomaly.setCostImpact(toBigDecimal(line[8]));
                anomaly.setCostImpactPercentage(toBigDecimal(line[9]));
                anomaly.setCost(toBigDecimal(line[10]));
                anomaly.setStatus(line[11]);
                anomaly.setFeedback(line[12]);
                anomaly.setCreatedAt(LocalDateTime.now());
                anomalies.add(anomaly);
            }
            costAnomalyRepository.saveAll(anomalies);
            log.info("Imported {} cost anomalies", anomalies.size());
        } catch (Exception e) {
            log.error("Error importing cost anomalies: {}", e.getMessage());
            throw new RuntimeException("Failed to import cost anomalies", e);
        }
    }

    private void importForecastReport() {
        String resourcePath = "source/forecastReport.csv";
        log.info("Importing forecast report from {}", resourcePath);
        
        List<ForecastReport> reports = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(resourcePath).getInputStream()))) {
            String[] headers = reader.readNext(); // Skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                ForecastReport report = new ForecastReport();
                report.setServiceItems(line[0].replace("\"", ""));
                report.setActual092024(toBigDecimal(line[1]));
                report.setActual102024(toBigDecimal(line[2]));
                report.setActual112024(toBigDecimal(line[3]));
                report.setActual122024(toBigDecimal(line[4]));
                report.setActual012025(toBigDecimal(line[5]));
                report.setActual022025(toBigDecimal(line[6]));
                report.setActual032025(toBigDecimal(line[7]));
                report.setActual042025(toBigDecimal(line[8]));
                report.setActual052025(toBigDecimal(line[9]));
                report.setActual062025(toBigDecimal(line[10]));
                report.setActual072025(toBigDecimal(line[11]));
                report.setActual082025(toBigDecimal(line[12]));
                report.setForecasted092025(toBigDecimal(line[13]));
                report.setForecasted102025(toBigDecimal(line[14]));
                report.setForecasted112025(toBigDecimal(line[15]));
                report.setForecasted122025(toBigDecimal(line[16]));
                report.setForecasted012026(toBigDecimal(line[17]));
                report.setForecasted022026(toBigDecimal(line[18]));
                report.setForecasted032026(toBigDecimal(line[19]));
                report.setForecasted042026(toBigDecimal(line[20]));
                report.setForecasted052026(toBigDecimal(line[21]));
                report.setForecasted062026(toBigDecimal(line[22]));
                report.setForecasted072026(toBigDecimal(line[23]));
                report.setForecasted082026(toBigDecimal(line[24]));
                report.setTotal(toBigDecimal(line[25]));
                report.setCreatedAt(LocalDateTime.now());
                reports.add(report);
            }
            forecastReportRepository.saveAll(reports);
            log.info("Imported {} forecast reports", reports.size());
        } catch (Exception e) {
            log.error("Error importing forecast report: {}", e.getMessage());
            throw new RuntimeException("Failed to import forecast report", e);
        }
    }

    private void importReservationRecommendations() {
        String resourcePath = "source/reservationRecommendations.csv";
        log.info("Importing reservation recommendations from {}", resourcePath);
        
        List<ReservationRecommendation> recommendations = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(resourcePath).getInputStream()))) {
            String[] headers = reader.readNext(); // Skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                ReservationRecommendation recommendation = new ReservationRecommendation();
                recommendation.setLinkedAccountId(line[0]);
                recommendation.setAccountName(line[1]);
                recommendation.setInstanceRegion(line[2]);
                recommendation.setService(line[3]);
                recommendation.setInstanceType(line[4]);
                recommendation.setUsageType(line[5]);
                recommendation.setPlatform(line[6]);
                recommendation.setCurrentCoverage(toBigDecimal(line[7]));
                recommendation.setNewCoverage(toBigDecimal(line[8]));
                recommendation.setRecommendedPurchases(Integer.parseInt(line[9]));
                recommendation.setUpfrontCost(toBigDecimal(line[10]));
                recommendation.setMonthlyCost(toBigDecimal(line[11]));
                recommendation.setEstimatedMonthlySavings(toBigDecimal(line[12]));
                recommendation.setEstimatedYearlySavings(toBigDecimal(line[13]));
                recommendation.setCreatedAt(LocalDateTime.now());
                recommendations.add(recommendation);
            }
            reservationRecommendationRepository.saveAll(recommendations);
            log.info("Imported {} reservation recommendations", recommendations.size());
        } catch (Exception e) {
            log.error("Error importing reservation recommendations: {}", e.getMessage());
            throw new RuntimeException("Failed to import reservation recommendations", e);
        }
    }

    private void importRightsizingRecommendations() {
        String resourcePath = "source/rightsizingRecommendations.csv";
        log.info("Importing rightsizing recommendations from {}", resourcePath);
        
        List<RightsizingRecommendation> recommendations = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(resourcePath).getInputStream()))) {
            String[] headers = reader.readNext(); // Skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                RightsizingRecommendation recommendation = new RightsizingRecommendation();
                recommendation.setInstanceName(line[0]);
                recommendation.setInstanceId(line[1]);
                recommendation.setInstanceState(line[2]);
                recommendation.setLaunchDate(LocalDateTime.parse(line[3], DATE_FORMATTER));
                recommendation.setRegion(line[4]);
                recommendation.setOperatingSystem(line[5].toLowerCase());
                recommendation.setTags(line[6]);
                recommendation.setAutoScalingGroupName(StringUtils.hasText(line[7]) ? line[7] : null);
                recommendation.setTotalHours(toBigDecimal(line[8]));  // Changed from Integer.parseInt to toBigDecimal
                recommendation.setInstanceType(line[9]);
                recommendation.setAccountName(line[10]);
                recommendation.setCpuAvg(toBigDecimal(line[11]));
                recommendation.setMemoryAvg(toBigDecimal(line[12]));
                recommendation.setDiskAvg(toBigDecimal(line[13]));
                recommendation.setCpuMax(toBigDecimal(line[14]));
                recommendation.setMemoryMax(toBigDecimal(line[15]));
                recommendation.setDiskMax(toBigDecimal(line[16]));
                recommendation.setNetOutAvg(toBigDecimal(line[17]));
                recommendation.setStatus(line[18]);
                recommendation.setCurrentCost(toBigDecimal(line[19]));
                recommendation.setProjectedCost(toBigDecimal(line[20]));
                recommendation.setProjectedSavingsByCost(toBigDecimal(line[21]));
                recommendation.setCurrentPrice(toBigDecimal(line[22]));
                recommendation.setProjectedPrice(toBigDecimal(line[23]));
                recommendation.setProjectedSavings(toBigDecimal(line[24]));
                recommendation.setTerminateRecommendation(Boolean.parseBoolean(line[25]));
                recommendation.setBestFitInstanceAssetServiceTypeName(StringUtils.hasText(line[26]) ? line[26] : null);
                recommendation.setPerspectiveName1(StringUtils.hasText(line[27]) ? line[27] : null);
                recommendation.setPerspectiveGroupName1(StringUtils.hasText(line[28]) ? line[28] : null);
                recommendation.setPerspectiveName2(StringUtils.hasText(line[29]) ? line[29] : null);
                recommendation.setPerspectiveGroupName2(StringUtils.hasText(line[30]) ? line[30] : null);
                recommendation.setInFamilyRecommendation(StringUtils.hasText(line[31]) ? line[31] : null);
                recommendation.setInFamilyRecommendationProjectedCost(toBigDecimal(line[32]));
                recommendation.setInFamilyRecommendationProjectedPrice(toBigDecimal(line[33]));
                recommendation.setCreatedAt(LocalDateTime.now());
                recommendations.add(recommendation);
            }
            rightsizingRecommendationRepository.saveAll(recommendations);
            log.info("Imported {} rightsizing recommendations", recommendations.size());
        } catch (Exception e) {
            log.error("Error importing rightsizing recommendations: {}", e.getMessage());
            throw new RuntimeException("Failed to import rightsizing recommendations", e);
        }
    }
}