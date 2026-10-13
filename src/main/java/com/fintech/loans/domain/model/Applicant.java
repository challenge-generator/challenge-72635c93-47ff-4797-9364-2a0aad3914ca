package com.fintech.loans.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Representa a un solicitante de préstamo en el sistema.
 * Contiene la información personal y financiera necesaria para evaluar su elegibilidad.
 */
public record Applicant(
        @NotNull
        UUID id,

        @NotBlank(message = "El nombre completo es obligatorio")
        @Size(max = 100, message = "El nombre completo no puede exceder los 100 caracteres")
        String fullName,

        @NotBlank(message = "El número de identificación es obligatorio")
        @Size(max = 20, message = "El número de identificación no puede exceder los 20 caracteres")
        String identificationNumber,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        LocalDate birthDate,

        @NotBlank(message = "La dirección es obligatoria")
        @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
        String address,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres")
        String email,

        @NotBlank(message = "El número de teléfono es obligatorio")
        @Size(max = 20, message = "El número de teléfono no puede exceder los 20 caracteres")
        String phoneNumber,

        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser un valor positivo")
        BigDecimal monthlyIncome,

        @NotNull(message = "El puntaje de crédito es obligatorio")
        @Positive(message = "El puntaje de crédito debe ser un valor positivo")
        Integer creditScore,

        @NotBlank(message = "La ocupación es obligatoria")
        @Size(max = 50, message = "La ocupación no puede exceder los 50 caracteres")
        String occupation,

        @NotNull(message = "El estado de empleo es obligatorio")
        EmploymentStatus employmentStatus) {

    /**
     * Enumeración que representa el estado de empleo del solicitante.
     */
    public enum EmploymentStatus {
        EMPLOYED,
        SELF_EMPLOYED,
        UNEMPLOYED,
        RETIRED
    }

    /**
     * Valida que la información del solicitante sea consistente.
     * @throws IllegalArgumentException si alguna validación falla.
     */
    public void validate() {
        if (birthDate != null && birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("El solicitante debe ser mayor de 18 años");
        }
        if (creditScore != null && (creditScore < 300 || creditScore > 850)) {
            throw new IllegalArgumentException("El puntaje de crédito debe estar entre 300 y 850");
        }
    }
}