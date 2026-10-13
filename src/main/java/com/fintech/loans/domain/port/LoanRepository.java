package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.LoanApplication;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto que define las operaciones de persistencia para solicitudes de préstamo.
 * Las implementaciones concretas se proporcionan en la capa de infraestructura.
 */
public interface LoanRepository {
    /**
     * Guarda una solicitud de préstamo en el repositorio.
     * @param loanApplication la solicitud de préstamo a guardar.
     * @return la solicitud de préstamo guardada.
     */
    LoanApplication save(LoanApplication loanApplication);

    /**
     * Busca una solicitud de préstamo por su identificador.
     * @param id el identificador de la solicitud de préstamo.
     * @return un Optional que contiene la solicitud de préstamo si existe, o vacío si no.
     */
    Optional<LoanApplication> findById(UUID id);

    /**
     * Busca todas las solicitudes de préstamo de un solicitante específico.
     * @param applicantId el identificador del solicitante.
     * @return una lista de solicitudes de préstamo asociadas al solicitante.
     */
    List<LoanApplication> findByApplicantId(UUID applicantId);

    /**
     * Busca todas las solicitudes de préstamo con un estado específico.
     * @param status el estado de las solicitudes de préstamo.
     * @return una lista de solicitudes de préstamo con el estado especificado.
     */
    List<LoanApplication> findByStatus(String status);

    /**
     * Elimina una solicitud de préstamo del repositorio.
     * @param id el identificador de la solicitud de préstamo a eliminar.
     */
    void deleteById(UUID id);
}