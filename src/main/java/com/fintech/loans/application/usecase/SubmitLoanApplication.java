package com.fintech.loans.application.usecase;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.CreditBureauService;
import com.fintech.loans.domain.port.LoanRepository;
import com.fintech.loans.domain.port.RiskEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class SubmitLoanApplication {

    private static final Logger logger = LoggerFactory.getLogger(SubmitLoanApplication.class);
    private static final int MIN_CREDIT_SCORE = 600;
    private static final int MAX_RISK_SCORE = 70;
    private static final BigDecimal MIN_INCOME_RATIO = BigDecimal.valueOf(0.4);

    private final LoanRepository loanRepository;
    private final CreditBureauService creditBureauService;
    private final RiskEngineService riskEngineService;

    @Autowired
    public SubmitLoanApplication(LoanRepository loanRepository,
                                  CreditBureauService creditBureauService,
                                  RiskEngineService riskEngineService) {
        this.loanRepository = loanRepository;
        this.creditBureauService = creditBureauService;
        this.riskEngineService = riskEngineService;
    }

    @Transactional
    public LoanApplication execute(Applicant applicant, BigDecimal amount, 
                                   Integer termMonths, String purpose) {
        logger.info("Iniciando procesamiento de solicitud de préstamo para applicant: {}", applicant);

        validarSolicitud(applicant, amount, termMonths);

        LoanApplication loanApplication = new LoanApplication(
            applicant.id(),
            amount,
            termMonths,
            calcularTasaInteres(applicant),
            purpose
        );

        loanApplication = loanRepository.save(loanApplication);
        logger.info("Solicitud {} guardada con estado: {}", loanApplication.getId(), loanApplication.getStatus());

        loanApplication.markAsCreditCheck();
        loanApplication = loanRepository.save(loanApplication);

        Integer creditScore = obtenerCreditScore(applicant).join();
        loanApplication.setCreditScore(creditScore);
        logger.info("Credit score obtenido: {}", creditScore);

        if (!evaluarCreditScore(creditScore)) {
            String reason = "Credit score insuficiente: " + creditScore;
            logger.warn("Solicitud {} rechazada por credit score bajo: {}", loanApplication.getId(), creditScore);
            loanApplication.reject(reason);
            return loanRepository.save(loanApplication);
        }

        loanApplication.markAsRiskAssessment();
        loanApplication = loanRepository.save(loanApplication);

        Integer riskScore = evaluarRiesgo(loanApplication, applicant).join();
        loanApplication.setRiskScore(riskScore);
        logger.info("Risk score obtenido: {}", riskScore);

        if (!evaluarRiskScore(riskScore)) {
            String reason = "Risk score demasiado alto: " + riskScore;
            logger.warn("Solicitud {} rechazada por risk score alto: {}", loanApplication.getId(), riskScore);
            loanApplication.reject(reason);
            return loanRepository.save(loanApplication);
        }

        loanApplication.calculateMonthlyPayment();
        loanApplication.approve();
        logger.info("Solicitud {} aprobada con mensualidad: {}", 
                   loanApplication.getId(), loanApplication.getMonthlyPayment());

        return loanRepository.save(loanApplication);
    }

    private void validarSolicitud(Applicant applicant, BigDecimal amount, Integer termMonths) {
        if (applicant == null) {
            throw new IllegalArgumentException("El solicitante no puede ser nulo");
        }
        applicant.validate();

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (amount.compareTo(BigDecimal.valueOf(1000)) < 0) {
            throw new IllegalArgumentException("El monto mínimo del préstamo es 1000");
        }
        if (amount.compareTo(BigDecimal.valueOf(500000)) > 0) {
            throw new IllegalArgumentException("El monto máximo del préstamo es 500000");
        }

        if (termMonths == null || termMonths < 6 || termMonths > 120) {
            throw new IllegalArgumentException("El plazo debe estar entre 6 y 120 meses");
        }
    }

    private BigDecimal calcularTasaInteres(Applicant applicant) {
        BigDecimal baseRate = BigDecimal.valueOf(0.15);
        BigDecimal employmentAdjustment = switch (applicant.employmentStatus()) {
            case EMPLOYED -> BigDecimal.valueOf(-0.02);
            case SELF_EMPLOYED -> BigDecimal.valueOf(0.01);
            case UNEMPLOYED -> BigDecimal.valueOf(0.05);
            case RETIRED -> BigDecimal.valueOf(0.00);
        };

        BigDecimal incomeAdjustment = BigDecimal.ZERO;
        if (applicant.monthlyIncome() != null) {
            if (applicant.monthlyIncome().compareTo(BigDecimal.valueOf(10000)) > 0) {
                incomeAdjustment = BigDecimal.valueOf(-0.01);
            } else if (applicant.monthlyIncome().compareTo(BigDecimal.valueOf(3000)) < 0) {
                incomeAdjustment = BigDecimal.valueOf(0.02);
            }
        }

        return baseRate.add(employmentAdjustment).add(incomeAdjustment)
                .setScale(4, RoundingMode.HALF_UP);
    }

    private CompletableFuture<Integer> obtenerCreditScore(Applicant applicant) {
        return creditBureauService.getCreditScore(applicant)
            .exceptionally(ex -> {
                logger.error("Error al obtener credit score, usando default: {}", ex.getMessage());
                return 650;
            });
    }

    private boolean evaluarCreditScore(Integer creditScore) {
        return creditScore != null && creditScore >= MIN_CREDIT_SCORE;
    }

    private CompletableFuture<Integer> evaluarRiesgo(LoanApplication loanApplication, Applicant applicant) {
        return riskEngineService.assessRisk(loanApplication, applicant)
            .exceptionally(ex -> {
                logger.error("Error al evaluar riesgo, usando default: {}", ex.getMessage());
                return 50;
            });
    }

    private boolean evaluarRiskScore(Integer riskScore) {
        return riskScore != null && riskScore <= MAX_RISK_SCORE;
    }

    public LoanApplication findById(UUID id) {
        return loanRepository.findById(id).orElse(null);
    }

    public LoanApplication findByApplicantId(UUID applicantId) {
        return loanRepository.findByApplicantId(applicantId).stream().findFirst().orElse(null);
    }
}