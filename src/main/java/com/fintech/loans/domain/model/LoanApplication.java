package com.fintech.loans.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan_applications", indexes = {
    @Index(name = "idx_applicant_id", columnList = "applicant_id"),
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "applicant_id", nullable = false)
    private UUID applicantId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    @NotNull(message = "El monto del préstamo es obligatorio")
    @DecimalMin(value = "1000.00", message = "El monto mínimo es 1000")
    @DecimalMax(value = "500000.00", message = "El monto máximo es 500000")
    private BigDecimal amount;

    @Column(name = "term_months", nullable = false)
    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 6, message = "El plazo mínimo es 6 meses")
    @Max(value = 120, message = "El plazo máximo es 120 meses")
    private Integer termMonths;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    @NotNull(message = "La tasa de interés es obligatoria")
    private BigDecimal interestRate;

    @Column(name = "monthly_payment", precision = 19, scale = 4)
    private BigDecimal monthlyPayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status;

    @Column(name = "purpose", length = 500)
    private String purpose;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "risk_score")
    private Integer riskScore;

    @Column(name = "rejection_reason", length = 1000)
    private String rejectionReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "version")
    private Long version;

    public enum LoanStatus {
        SUBMITTED,
        CREDIT_CHECK,
        RISK_ASSESSMENT,
        APPROVED,
        REJECTED,
        DISBURSED,
        CANCELLED
    }

    public LoanApplication() {
    }

    public LoanApplication(UUID applicantId, BigDecimal amount, Integer termMonths, 
                           BigDecimal interestRate, String purpose) {
        this.applicantId = applicantId;
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.purpose = purpose;
        this.status = LoanStatus.SUBMITTED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (status == null) status = LoanStatus.SUBMITTED;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void calculateMonthlyPayment() {
        if (amount != null && termMonths != null && interestRate != null) {
            BigDecimal monthlyRate = interestRate.divide(BigDecimal.valueOf(12), 8, java.math.RoundingMode.HALF_UP);
            BigDecimal factor = monthlyRate.add(BigDecimal.ONE).pow(termMonths);
            BigDecimal monthly = amount.multiply(monthlyRate).multiply(factor)
                    .divide(factor.subtract(BigDecimal.ONE), 4, java.math.RoundingMode.HALF_UP);
            this.monthlyPayment = monthly;
        }
    }

    public void approve() {
        this.status = LoanStatus.APPROVED;
        this.updatedAt = LocalDateTime.now();
    }

    public void reject(String reason) {
        this.status = LoanStatus.REJECTED;
        this.rejectionReason = reason;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsCreditCheck() {
        this.status = LoanStatus.CREDIT_CHECK;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsRiskAssessment() {
        this.status = LoanStatus.RISK_ASSESSMENT;
        this.updatedAt = LocalDateTime.now();
    }

    public void disburse() {
        this.status = LoanStatus.DISBURSED;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = LoanStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isApproved() {
        return status == LoanStatus.APPROVED;
    }

    public boolean isRejected() {
        return status == LoanStatus.REJECTED;
    }

    public boolean canTransitionTo(LoanStatus newStatus) {
        return switch (this.status) {
            case SUBMITTED -> newStatus == LoanStatus.CREDIT_CHECK;
            case CREDIT_CHECK -> newStatus == LoanStatus.RISK_ASSESSMENT || newStatus == LoanStatus.REJECTED;
            case RISK_ASSESSMENT -> newStatus == LoanStatus.APPROVED || newStatus == LoanStatus.REJECTED;
            case APPROVED -> newStatus == LoanStatus.DISBURSED || newStatus == LoanStatus.CANCELLED;
            default -> false;
        };
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(UUID applicantId) {
        this.applicantId = applicantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}