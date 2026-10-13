package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;

import java.util.concurrent.CompletableFuture;

public interface RiskEngineService {

    CompletableFuture<Integer> assessRisk(LoanApplication loanApplication, Applicant applicant) 
            throws RiskEngineException;

    CompletableFuture<RiskCategory> categorizeRisk(LoanApplication loanApplication, Applicant applicant)
            throws RiskEngineException;

    class RiskEngineException extends Exception {
        private final String errorCode;
        private final String details;

        public RiskEngineException(String message) {
            super(message);
            this.errorCode = "RISK_ENGINE_ERROR";
            this.details = null;
        }

        public RiskEngineException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
            this.details = null;
        }

        public RiskEngineException(String message, String errorCode, String details) {
            super(message);
            this.errorCode = errorCode;
            this.details = details;
        }

        public RiskEngineException(String message, Throwable cause) {
            super(message, cause);
            this.errorCode = "RISK_ENGINE_ERROR";
            this.details = null;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getDetails() {
            return details;
        }
    }

    enum RiskCategory {
        LOW(0, 30, "Riesgo bajo - Aprobación automática"),
        MEDIUM(31, 60, "Riesgo medio - Requiere revisión manual"),
        HIGH(61, 80, "Riesgo alto - Requiere aprobación especial"),
        VERY_HIGH(81, 100, "Riesgo muy alto - Rechazo automático");

        private final int minScore;
        private final int maxScore;
        private final String description;

        RiskCategory(int minScore, int maxScore, String description) {
            this.minScore = minScore;
            this.maxScore = maxScore;
            this.description = description;
        }

        public int getMinScore() {
            return minScore;
        }

        public int getMaxScore() {
            return maxScore;
        }

        public String getDescription() {
            return description;
        }

        public static RiskCategory fromScore(Integer score) {
            if (score == null) {
                return MEDIUM;
            }
            for (RiskCategory category : values()) {
                if (score >= category.minScore && score <= category.maxScore) {
                    return category;
                }
            }
            return VERY_HIGH;
        }
    }
}