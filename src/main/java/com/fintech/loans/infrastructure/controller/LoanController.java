package com.fintech.loans.infrastructure.controller;


import com.fintech.loans.domain.model.EmploymentStatus;
import com.fintech.loans.application.usecase.SubmitLoanApplication;
import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.LoanRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Gestión de Préstamos", description = "Endpoints para gestionar solicitudes de préstamos")
public class LoanController {

    private final SubmitLoanApplication submitLoanApplication;
    private final LoanRepository loanRepository;

    public LoanController(SubmitLoanApplication submitLoanApplication, LoanRepository loanRepository) {
        this.submitLoanApplication = submitLoanApplication;
        this.loanRepository = loanRepository;
    }

    @PostMapping
    @Operation(summary = "Crear solicitud de préstamo", description = "Envía una nueva solicitud de préstamo para procesamiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente",
                    content = @Content(schema = @Schema(implementation = LoanApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "503", description = "Servicio externo no disponible")
    })
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la solicitud de préstamo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoanApplicationRequest.class))
            )
            @RequestBody LoanApplicationRequest request) {

        Applicant applicant = new Applicant(
                request.applicantId(),
                request.fullName(),
                request.email(),
                request.annualIncome(),
                Applicant.EmploymentStatus.valueOf(request.employmentStatus())
        );

        LoanApplication loanApplication = submitLoanApplication.execute(
                applicant,
                request.requestedAmount(),
                request.termMonths(),
                request.purpose()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(LoanApplicationResponse.fromDomain(loanApplication));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener solicitud por ID", description = "Recupera los detalles de una solicitud de préstamo específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<LoanApplicationResponse> getLoanApplicationById(
            @Parameter(description = "ID de la solicitud de préstamo", required = true)
            @PathVariable UUID id) {

        return loanRepository.findById(id)
                .map(loan -> ResponseEntity.ok(LoanApplicationResponse.fromDomain(loan)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar solicitudes", description = "Lista todas las solicitudes de préstamo con filtros opcionales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes")
    })
    public ResponseEntity<List<LoanApplicationResponse>> getAllLoanApplications(
            @Parameter(description = "Filtrar por ID del solicitante")
            @RequestParam(required = false) UUID applicantId,
            @Parameter(description = "Filtrar por estado de la solicitud")
            @RequestParam(required = false) String status) {

        List<LoanApplication> applications;

        if (applicantId != null) {
            applications = loanRepository.findByApplicantId(applicantId);
        } else if (status != null) {
            applications = loanRepository.findByStatus(status);
        } else {
            applications = loanRepository.findAll();
        }

        List<LoanApplicationResponse> responses = applications.stream()
                .map(LoanApplicationResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud", description = "Elimina una solicitud de préstamo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitud eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<Void> deleteLoanApplication(
            @Parameter(description = "ID de la solicitud de préstamo", required = true)
            @PathVariable UUID id) {

        if (loanRepository.findById(id).isPresent()) {
            loanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public record LoanApplicationRequest(
            UUID applicantId,
            String fullName,
            String email,
            BigDecimal annualIncome,
            String employmentStatus,
            BigDecimal requestedAmount,
            Integer termMonths,
            String purpose
    ) {}

    public record LoanApplicationResponse(
            UUID id,
            UUID applicantId,
            String fullName,
            String email,
            BigDecimal annualIncome,
            String employmentStatus,
            BigDecimal requestedAmount,
            Integer termMonths,
            String purpose,
            String status,
            Integer creditScore,
            String riskLevel
    ) {
        public static LoanApplicationResponse fromDomain(LoanApplication loan) {
            return new LoanApplicationResponse(
                    loan.getId(),
                    loan.getApplicantId(),
                    loan.getApplicantFullName(),
                    loan.getApplicantEmail(),
                    loan.getAnnualIncome(),
                    loan.getEmploymentStatus().name(),
                    loan.getRequestedAmount(),
                    loan.getTermMonths(),
                    loan.getPurpose(),
                    loan.getStatus(),
                    loan.getCreditScore(),
                    loan.getRiskLevel()
            );
        }
    }

    public record ErrorResponse(
            int status,
            String error,
            String message
    ) {}
}