package com.fintech.loans.infrastructure.adapter;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.port.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentAdapter implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentAdapter.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private final RestTemplate restTemplate;
    private final String paymentServiceUrl;
    private final String paymentServiceApiKey;
    private final boolean sandboxMode;

    public PaymentAdapter(
            @Value("${external-services.payment.url:http://localhost:8082/api/payments}") String paymentServiceUrl,
            @Value("${external-services.payment.api-key:demo-key}") String paymentServiceApiKey,
            @Value("${external-services.payment.sandbox-mode:true}") boolean sandboxMode) {
        this.restTemplate = new RestTemplate();
        this.paymentServiceUrl = paymentServiceUrl;
        this.paymentServiceApiKey = paymentServiceApiKey;
        this.sandboxMode = sandboxMode;
    }

    @Override
    public CompletableFuture<PaymentResult> processDisbursement(
            UUID loanId,
            Applicant recipient,
            BigDecimal amount) {

        log.info("Iniciando desembolso para préstamo: {} monto: {} a: {}", 
                loanId, amount, recipient.email());

        return CompletableFuture.supplyAsync(() -> {
            try {
                DisbursementRequest request = new DisbursementRequest(
                        loanId.toString(),
                        recipient.identificationNumber(),
                        recipient.email(),
                        recipient.phoneNumber(),
                        amount,
                        LocalDate.now().plusDays(1).format(DATE_FORMATTER)
                );

                DisbursementResponse response = callPaymentService(request);

                return mapToPaymentResult(response, loanId);
            } catch (Exception e) {
                log.error("Error al procesar desembolso para préstamo: {}", loanId, e);
                return PaymentResult.builder()
                        .success(false)
                        .transactionId(null)
                        .errorMessage("Error al procesar desembolso: " + e.getMessage())
                        .build();
            }
        });
    }

    @Override
    public CompletableFuture<PaymentResult> processPayment(
            UUID loanId,
            UUID borrowerId,
            BigDecimal amount) {

        log.info("Iniciando proceso de pago para préstamo: {} monto: {}", loanId, amount);

        return CompletableFuture.supplyAsync(() -> {
            try {
                PaymentRequest request = new PaymentRequest(
                        loanId.toString(),
                        borrowerId.toString(),
                        amount,
                        LocalDate.now().format(DATE_FORMATTER)
                );

                PaymentResponse response = callCollectPayment(request);

                return mapToCollectPaymentResult(response, loanId);
            } catch (Exception e) {
                log.error("Error al procesar pago para préstamo: {}", loanId, e);
                return PaymentResult.builder()
                        .success(false)
                        .transactionId(null)
                        .errorMessage("Error al procesar pago: " + e.getMessage())
                        .build();
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> validatePaymentMethod(String paymentMethodId) {
        log.info("Validando método de pago: {}", paymentMethodId);

        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<String, Object> response = restTemplate.getForObject(
                        paymentServiceUrl + "/methods/{id}/validate",
                        Map.class,
                        paymentMethodId
                );

                return response != null && Boolean.TRUE.equals(response.get("valid"));
            } catch (Exception e) {
                log.warn("Error al validar método de pago, retornando false: {}", e.getMessage());
                return false;
            }
        });
    }

    private DisbursementResponse callPaymentService(DisbursementRequest request) {
        try {
            Map<String, Object> body = Map.of(
                    "loanId", request.loanId(),
                    "identification", request.identification(),
                    "email", request.email(),
                    "phone", request.phone(),
                    "amount", request.amount(),
                    "scheduledDate", request.scheduledDate()
            );

            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.postForObject(
                    paymentServiceUrl + "/disbursement",
                    body,
                    Map.class
            );

            if (response == null) {
                throw new RuntimeException("Respuesta nula del servicio de pagos");
            }

            return DisbursementResponse.builder()
                    .success(Boolean.TRUE.equals(response.get("success")))
                    .transactionId((String) response.get("transactionId"))
                    .message((String) response.getOrDefault("message", ""))
                    .build();
        } catch (Exception e) {
            log.warn("Error en llamada al servicio de pagos, retornando respuesta por defecto");
            return DisbursementResponse.builder()
                    .success(false)
                    .transactionId(null)
                    .message("Servicio de pagos no disponible")
                    .build();
        }
    }

    private PaymentResponse callCollectPayment(PaymentRequest request) {
        try {
            Map<String, Object> body = Map.of(
                    "loanId", request.loanId(),
                    "borrowerId", request.borrowerId(),
                    "amount", request.amount(),
                    "paymentDate", request.paymentDate()
            );

            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.postForObject(
                    paymentServiceUrl + "/collect",
                    body,
                    Map.class
            );

            if (response == null) {
                throw new RuntimeException("Respuesta nula del servicio de cobros");
            }

            return PaymentResponse.builder()
                    .success(Boolean.TRUE.equals(response.get("success")))
                    .transactionId((String) response.get("transactionId"))
                    .message((String) response.getOrDefault("message", ""))
                    .build();
        } catch (Exception e) {
            log.warn("Error en llamada al servicio de cobros, retornando respuesta por defecto");
            return PaymentResponse.builder()
                    .success(false)
                    .transactionId(null)
                    .message("Servicio de cobros no disponible")
                    .build();
        }
    }

    private PaymentResult mapToPaymentResult(DisbursementResponse response, UUID loanId) {
        return PaymentResult.builder()
                .success(response.isSuccess())
                .transactionId(response.transactionId())
                .errorMessage(response.isSuccess() ? null : response.message())
                .build();
    }

    private PaymentResult mapToCollectPaymentResult(PaymentResponse response, UUID loanId) {
        return PaymentResult.builder()
                .success(response.isSuccess())
                .transactionId(response.transactionId())
                .errorMessage(response.isSuccess() ? null : response.message())
                .build();
    }

    public record DisbursementRequest(
            String loanId,
            String identification,
            String email,
            String phone,
            BigDecimal amount,
            String scheduledDate
    ) {}

    public record DisbursementResponse(
            boolean success,
            String transactionId,
            String message
    ) {
        public static DisbursementResponseBuilder builder() {
            return new DisbursementResponseBuilder();
        }
    }

    public static class DisbursementResponseBuilder {
        private boolean success;
        private String transactionId;
        private String message;

        public DisbursementResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public DisbursementResponseBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public DisbursementResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public DisbursementResponse build() {
            return new DisbursementResponse(success, transactionId, message);
        }
    }

    public record PaymentRequest(
            String loanId,
            String borrowerId,
            BigDecimal amount,
            String paymentDate
    ) {}

    public record PaymentResponse(
            boolean success,
            String transactionId,
            String message
    ) {
        public static PaymentResponseBuilder builder() {
            return new PaymentResponseBuilder();
        }
    }

    public static class PaymentResponseBuilder {
        private boolean success;
        private String transactionId;
        private String message;

        public PaymentResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public PaymentResponseBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public PaymentResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public PaymentResponse build() {
            return new PaymentResponse(success, transactionId, message);
        }
    }
}