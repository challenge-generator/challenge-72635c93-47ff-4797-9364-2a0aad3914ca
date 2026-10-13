package com.fintech.loans.infrastructure.adapter;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.port.RiskEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class RiskEngineAdapter implements RiskEngineService {

    private static final Logger log = LoggerFactory.getLogger(RiskEngineAdapter.class);

    private final RestTemplate restTemplate;
    private final String riskEngineUrl;
    private final String riskEngineApiKey;

    public RiskEngineAdapter(
            @Value("${external-services.risk-engine.url:http://localhost:8081/api/risk}") String riskEngineUrl,
            @Value("${external-services.risk-engine.api-key:demo-key}") String riskEngineApiKey) {
        this.restTemplate = new RestTemplate();
        this.riskEngineUrl = riskEngineUrl;
        this.riskEngineApiKey = riskEngineApiKey;
    }

    @Override
    public CompletableFuture<RiskEvaluation> evaluateRisk(Applicant applicant, BigDecimal requestedAmount) {
        log.info("Iniciando evaluación de riesgo para solicitante: {} con monto: {}", 
                applicant.email(), requestedAmount);

        return CompletableFuture.supplyAsync(() -> {
            try {
                RiskRequest request = new RiskRequest(
                        applicant.identificationNumber(),
                        applicant.email(),
                        requestedAmount,
                        applicant.employmentStatus().name()
                );

                RiskResponse response = callRiskEngine(request);

                return mapToRiskEvaluation(response);
            } catch (Exception e) {
                log.error("Error al evaluar riesgo para solicitante: {}", applicant.email(), e);
                return RiskEvaluation.builder()
                        .approved(false)
                        .riskScore(0)
                        .riskLevel(RiskLevel.HIGH)
                        .reason("Error en la evaluación de riesgo: " + e.getMessage())
                        .build();
            }
        });
    }

    private RiskResponse callRiskEngine(RiskRequest request) {
        try {
            Map<String, Object> body = Map.of(
                    "identification", request.identification(),
                    "email", request.email(),
                    "amount", request.amount(),
                    "employmentStatus", request.employmentStatus()
            );

            Map<String, Object> response = restTemplate.postForObject(
                    riskEngineUrl + "/evaluate",
                    body,
                    Map.class
            );

            if (response == null) {
                throw new RuntimeException("Respuesta nula del motor de riesgo");
            }

            return RiskResponse.builder()
                    .approved((Boolean) response.getOrDefault("approved", false))
                    .score(((Number) response.getOrDefault("score", 0)).intValue())
                    .level((String) response.getOrDefault("level", "HIGH"))
                    .message((String) response.getOrDefault("message", ""))
                    .build();
        } catch (Exception e) {
            log.warn("Error en llamada al motor de riesgo, retornando evaluación por defecto: {}", e.getMessage());
            return RiskResponse.builder()
                    .approved(false)
                    .score(0)
                    .level("HIGH")
                    .message("Servicio de riesgo no disponible")
                    .build();
        }
    }

    private RiskEvaluation mapToRiskEvaluation(RiskResponse response) {
        RiskLevel level = switch (response.getLevel().toUpperCase()) {
            case "LOW" -> RiskLevel.LOW;
            case "MEDIUM" -> RiskLevel.MEDIUM;
            default -> RiskLevel.HIGH;
        };

        return RiskEvaluation.builder()
                .approved(response.isApproved())
                .riskScore(response.getScore())
                .riskLevel(level)
                .reason(response.getMessage())
                .build();
    }

    public record RiskRequest(
            String identification,
            String email,
            BigDecimal amount,
            String employmentStatus
    ) {}

    public record RiskResponse(
            boolean approved,
            int score,
            String level,
            String message
    ) {
        public static RiskResponseBuilder builder() {
            return new RiskResponseBuilder();
        }
    }

    public static class RiskResponseBuilder {
        private boolean approved;
        private int score;
        private String level;
        private String message;

        public RiskResponseBuilder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public RiskResponseBuilder score(int score) {
            this.score = score;
            return this;
        }

        public RiskResponseBuilder level(String level) {
            this.level = level;
            return this;
        }

        public RiskResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public RiskResponse build() {
            return new RiskResponse(approved, score, level, message);
        }
    }
}