package com.fintech.loans.infrastructure.adapter;

import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.LoanRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador de persistencia que implementa el puerto LoanRepository
 * utilizando Spring Data JPA para interagir con la base de datos PostgreSQL.
 */
@Repository
public interface LoanJpaRepository extends JpaRepository<LoanApplication, UUID>, LoanRepository {

    @Override
    default LoanApplication save(LoanApplication loanApplication) {
        if (loanApplication.getId() == null) {
            return this.save(loanApplication);
        }
        return this.save(loanApplication);
    }

    @Override
    Optional<LoanApplication> findById(UUID id);

    @Override
    List<LoanApplication> findByApplicantId(UUID applicantId);

    @Override
    List<LoanApplication> findByStatus(String status);

    @Override
    void deleteById(UUID id);

    /**
     * Busca solicitudes de préstamo por rango de fecha de creación.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.createdAt BETWEEN :startDate AND :endDate")
    List<LoanApplication> findByCreatedAtBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /**
     * Busca solicitudes de préstamo por identificador de solicitante y estado.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.applicant.id = :applicantId AND l.status = :status")
    List<LoanApplication> findByApplicantIdAndStatus(
            @Param("applicantId") UUID applicantId,
            @Param("status") String status);

    /**
     * Cuenta el número de solicitudes de préstamo por estado.
     */
    @Query("SELECT COUNT(l) FROM LoanApplication l WHERE l.status = :status")
    long countByStatus(@Param("status") String status);

    /**
     * Busca solicitudes de préstamo con monto mayor al especificado.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.requestedAmount > :amount")
    List<LoanApplication> findByRequestedAmountGreaterThan(@Param("amount") java.math.BigDecimal amount);

    /**
     * Busca solicitudes de préstamo que están pendientes de revisión por más de
     * un número de días especificado.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.status = 'PENDING' AND l.createdAt < :thresholdDate")
    List<LoanApplication> findPendingApplicationsOlderThan(@Param("thresholdDate") LocalDateTime thresholdDate);
}