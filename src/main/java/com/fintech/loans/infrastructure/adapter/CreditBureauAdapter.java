package com.fintech.loans.infrastructure.adapter;



import com.fintech.loans.domain.model.EmploymentStatus;
import com.fintech.loans.domain.port.CreditBureauException;
import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.port.CreditBureauService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Adaptador que implementa la integración con el servicio externo de buró de crédito.
 * Provee métodos para obtener el puntaje crediticio y verificar el historial crediticio
 * de los solicitantes mediante llamadas al sistema externo del buró de crédito.
 */
@Service
public class CreditBureauAdapter implements CreditBureauService {

    private static final Logger logger = LoggerFactory.getLogger(CreditBureauAdapter.class);
    private final ExecutorService executorService;
    private final CreditBureauConfig config;

    public CreditBureauAdapter(CreditBureauConfig config) {
        this.executorService = Executors.newCachedThreadPool();
        this.config = config;
    }

    @Override
    public CompletableFuture<Integer> getCreditScore(Applicant applicant) throws CreditBureauException {
        logger.info("Consultando puntaje crediticio para solicitante: {}", applicant.id());

        return CompletableFuture.supplyAsync(() -> {
            try {
                validateApplicant(applicant);
                Thread.sleep(config.getResponseDelay());

                if (config.isSimulateFailure() && Math.random() < 0.1) {
                    throw new CreditBureauException("Error de conexión con el buró de crédito",
                            new RuntimeException("Connection timeout"));
                }

                int score = calculateCreditScore(applicant);
                logger.info("Puntaje crediticio obtenido para {}: {}", applicant.id(), score);
                return score;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CreditBureauException("La consulta fue interrumpida", e);
            } catch (CreditBureauException e) {
                throw e;
            } catch (Exception e) {
                throw new CreditBureauException("Error al obtener puntaje crediticio", e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Boolean> hasCreditHistory(Applicant applicant) throws CreditBureauException {
        logger.info("Verificando historial crediticio para solicitante: {}", applicant.id());

        return CompletableFuture.supplyAsync(() -> {
            try {
                validateApplicant(applicant);
                Thread.sleep(config.getResponseDelay() / 2);

                boolean hasHistory = calculateCreditHistory(applicant);
                logger.info("Historial crediticio para {}: {}", applicant.id(), hasHistory ? "existe" : "no existe");
                return hasHistory;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CreditBureauException("La consulta fue interrumpida", e);
            } catch (CreditBureauException e) {
                throw e;
            } catch (Exception e) {
                throw new CreditBureauException("Error al verificar historial crediticio", e);
            }
        }, executorService);
    }

    private void validateApplicant(Applicant applicant) throws CreditBureauException {
        if (applicant == null) {
            throw new CreditBureauException("El solicitante no puede ser null");
        }
        if (applicant.id() == null) {
            throw new CreditBureauException("El identificador del solicitante no puede ser null");
        }
        if (applicant.identificationNumber() == null || applicant.identificationNumber().isBlank()) {
            throw new CreditBureauException("El número de identificación no puede estar vacío");
        }
    }

    private int calculateCreditScore(Applicant applicant) {
        int baseScore = 650;

        if (applicant.employmentStatus() == Applicant.EmploymentStatus.EMPLOYED) {
            baseScore += 50;
        } else if (applicant.employmentStatus() == Applicant.EmploymentStatus.SELF_EMPLOYED) {
            baseScore += 30;
        }

        int age = applicant.age();
        if (age >= 25 && age <= 55) {
            baseScore += 20;
        } else if (age < 25) {
            baseScore -= 30;
        }

        if (applicant.monthlyIncome() != null) {
            if (applicant.monthlyIncome().doubleValue() > 5000) {
                baseScore += 50;
            } else if (applicant.monthlyIncome().doubleValue() > 3000) {
                baseScore += 25;
            }
        }

        return Math.max(300, Math.min(850, baseScore));
    }

    private boolean calculateCreditHistory(Applicant applicant) {
        return applicant.identificationNumber() != null
                && !applicant.identificationNumber().isEmpty()
                && applicant.identificationNumber().length() >= 8;
    }

    /**
     * Configuración del adaptador de buró de crédito.
     */
    public static class CreditBureauConfig {
        private final String endpoint;
        private final String apiKey;
        private final int responseDelay;
        private final boolean simulateFailure;

        public CreditBureauConfig(String endpoint, String apiKey, int responseDelay, boolean simulateFailure) {
            this.endpoint = endpoint;
            this.apiKey = apiKey;
            this.responseDelay = responseDelay;
            this.simulateFailure = simulateFailure;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public String getApiKey() {
            return apiKey;
        }

        public int getResponseDelay() {
            return responseDelay;
        }

        public boolean isSimulateFailure() {
            return simulateFailure;
        }
    }
}