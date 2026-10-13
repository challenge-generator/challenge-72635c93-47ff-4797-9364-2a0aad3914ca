package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.LoanApplication;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto para la integración con el sistema de pagos.
 * Define el contrato que debe implementar cualquier adaptador de pagos.
 */
public interface PaymentService {

    /**
     * Procesa el desembolso de un préstamo al solicitante.
     *
     * @param loanApplication la solicitud de préstamo aprobada
     * @param disbursementDate fecha programada para el desembolso
     * @return un CompletableFuture con el identificador de transacción del pago
     * @throws PaymentException si ocurre un error durante el procesamiento
     */
    CompletableFuture<String> disburseLoan(LoanApplication loanApplication, LocalDate disbursementDate)
            throws PaymentException;

    /**
     * Procesa el cobro de una cuota del préstamo.
     *
     * @param loanApplication la solicitud de préstamo asociada
     * @param amount cantidad a cobrar
     * @param dueDate fecha de vencimiento de la cuota
     * @return un CompletableFuture con el identificador de transacción del cobro
     * @throws PaymentException si ocurre un error durante el procesamiento
     */
    CompletableFuture<String> collectPayment(LoanApplication loanApplication, BigDecimal amount, LocalDate dueDate)
            throws PaymentException;

    /**
     * Verifica el estado de una transacción de pago existente.
     *
     * @param transactionId identificador de la transacción
     * @return un CompletableFuture con el estado de la transacción
     * @throws PaymentException si ocurre un error durante la verificación
     */
    CompletableFuture<PaymentStatus> getPaymentStatus(String transactionId) throws PaymentException;

    /**
     * Reembolsa un pago previously realizado.
     *
     * @param transactionId identificador de la transacción a reembolsar
     * @param reason motivo del reembolso
     * @return un CompletableFuture con el identificador de la transacción de reembolso
     * @throws PaymentException si ocurre un error durante el procesamiento
     */
    CompletableFuture<String> refundPayment(String transactionId, String reason) throws PaymentException;

    /**
     * Excepción personalizada para errores del servicio de pagos.
     */
    class PaymentException extends Exception {
        private final String transactionId;
        private final PaymentErrorCode errorCode;

        public PaymentException(String message, String transactionId, PaymentErrorCode errorCode) {
            super(message);
            this.transactionId = transactionId;
            this.errorCode = errorCode;
        }

        public PaymentException(String message, Throwable cause, String transactionId, PaymentErrorCode errorCode) {
            super(message, cause);
            this.transactionId = transactionId;
            this.errorCode = errorCode;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public PaymentErrorCode getErrorCode() {
            return errorCode;
        }
    }

    /**
     * Estados posibles de una transacción de pago.
     */
    enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        CANCELLED,
        REFUNDED
    }

    /**
     * Códigos de error para el servicio de pagos.
     */
    enum PaymentErrorCode {
        INSUFFICIENT_FUNDS,
        ACCOUNT_NOT_FOUND,
        INVALID_ACCOUNT,
        TRANSACTION_TIMEOUT,
        DUPLICATE_TRANSACTION,
        SYSTEM_ERROR,
        VALIDATION_ERROR
    }
}