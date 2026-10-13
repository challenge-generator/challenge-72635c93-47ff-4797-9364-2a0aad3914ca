package com.fintech.loans.application.usecase;



import com.fintech.loans.domain.port.CreditBureauException;
import com.fintech.loans.domain.model.EmploymentStatus;
import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.CreditBureauService;
import com.fintech.loans.domain.port.LoanRepository;
import com.fintech.loans.domain.port.PaymentService;
import com.fintech.loans.domain.port.RiskEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para SubmitLoanApplication")
class SubmitLoanApplicationTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private CreditBureauService creditBureauService;

    @Mock
    private RiskEngineService riskEngineService;

    @Mock
    private PaymentService paymentService;

    private SubmitLoanApplication submitLoanApplication;

    private Applicant validApplicant;

    @BeforeEach
    void setUp() {
        submitLoanApplication = new SubmitLoanApplication(
                loanRepository,
                creditBureauService,
                riskEngineService,
                paymentService
        );

        validApplicant = new Applicant(
                UUID.randomUUID(),
                "Juan Pérez García",
                "juan.perez@email.com",
                new BigDecimal("60000"),
                Applicant.EmploymentStatus.EMPLOYED
        );
    }

    @Test
    @DisplayName("Debería crear solicitud exitosamente cuando el solicitante tiene buen historial crediticio")
    void shouldCreateLoanApplicationSuccessfully() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(750));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("LOW"));
        when(loanRepository.save(any(LoanApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("10000"),
                24,
                "CONSUMO"
        );

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("PENDING", result.getStatus());
        assertEquals(750, result.getCreditScore());
        assertEquals("LOW", result.getRiskLevel());

        verify(loanRepository).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debería rechazar solicitud cuando el puntaje crediticio es muy bajo")
    void shouldRejectApplicationWhenCreditScoreIsLow() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(450));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("10000"),
                24,
                "CONSUMO"
        );

        assertEquals("REJECTED", result.getStatus());
        assertEquals("CREDIT_SCORE_TOO_LOW", result.getRejectionReason());

        verify(loanRepository).save(any(LoanApplication.class));
        verify(paymentService, never()).processPayment(any(), any());
    }

    @Test
    @DisplayName("Debería rechazar solicitud cuando el nivel de riesgo es alto")
    void shouldRejectApplicationWhenRiskLevelIsHigh() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(700));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("HIGH"));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("50000"),
                12,
                "CONSUMO"
        );

        assertEquals("REJECTED", result.getStatus());
        assertEquals("HIGH_RISK", result.getRejectionReason());

        verify(loanRepository).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debería aprobar solicitud cuando cumple todos los criterios")
    void shouldApproveApplicationWhenAllCriteriaMet() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(720));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("LOW"));
        when(paymentService.processPayment(any(), any()))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(loanRepository.save(any(LoanApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("15000"),
                36,
                "CONSUMO"
        );

        assertEquals("APPROVED", result.getStatus());
        verify(paymentService).processPayment(any(), any());
    }

    @Test
    @DisplayName("Debería guardar la solicitud en repositorio con datos correctos")
    void shouldSaveApplicationWithCorrectData() throws Exception {
        BigDecimal requestedAmount = new BigDecimal("20000");
        Integer termMonths = 48;
        String purpose = "CONSUMO";

        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(680));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("MEDIUM"));
        when(loanRepository.save(any(LoanApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                requestedAmount,
                termMonths,
                purpose
        );

        ArgumentCaptor<LoanApplication> captor = ArgumentCaptor.forClass(LoanApplication.class);
        verify(loanRepository).save(captor.capture());

        LoanApplication savedApplication = captor.getValue();
        assertEquals(validApplicant.id(), savedApplication.getApplicantId());
        assertEquals(validApplicant.fullName(), savedApplication.getApplicantFullName());
        assertEquals(validApplicant.email(), savedApplication.getApplicantEmail());
        assertEquals(requestedAmount, savedApplication.getRequestedAmount());
        assertEquals(termMonths, savedApplication.getTermMonths());
        assertEquals(purpose, savedApplication.getPurpose());
    }

    @Test
    @DisplayName("Debería manejar excepción del servicio de buró de crédito")
    void shouldHandleCreditBureauException() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.failedFuture(
                        new CreditBureauService.CreditBureauException("Servicio no disponible")));

        assertThrows(Exception.class, () -> submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("10000"),
                24,
                "CONSUMO"
        ));

        verify(loanRepository, never()).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debería rechazar solicitante sin historial crediticio")
    void shouldRejectApplicantWithoutCreditHistory() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(0));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(false));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("5000"),
                12,
                "CONSUMO"
        );

        assertEquals("REJECTED", result.getStatus());
        assertEquals("NO_CREDIT_HISTORY", result.getRejectionReason());
    }
}