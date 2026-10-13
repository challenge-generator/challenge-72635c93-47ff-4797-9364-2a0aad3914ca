package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.Applicant;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto que define la integración con un servicio externo de buró de crédito.
 * Las implementaciones concretas se proporcionan en la capa de infraestructura.
 */
public interface CreditBureauService {
    /**
     * Obtiene el puntaje de crédito de un solicitante desde el buró de crédito.
     * @param applicant la información del solicitante.
     * @return un CompletableFuture que resuelve con el puntaje de crédito del solicitante.
     * @throws CreditBureauException si ocurre un error al consultar el buró de crédito.
     */
    CompletableFuture<Integer> getCreditScore(Applicant applicant) throws CreditBureauException;

    /**
     * Verifica si el solicitante tiene historial crediticio en el buró.
     * @param applicant la información del solicitante.
     * @return un CompletableFuture que resuelve con true si el solicitante tiene historial, false si no.
     * @throws CreditBureauException si ocurre un error al consultar el buró de crédito.
     */
    CompletableFuture<Boolean> hasCreditHistory(Applicant applicant) throws CreditBureauException;

    /**
     * Excepción personalizada para errores en la integración con el buró de crédito.
     */
    class CreditBureauException extends Exception {
        public CreditBureauException(String message) {
            super(message);
        }

        public CreditBureauException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}