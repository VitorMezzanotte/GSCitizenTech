package br.com.citizentech.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PontoRiscoRequest(
        @NotBlank(message = "nomeRua é obrigatório") @Size(max = 120, message = "nomeRua aceita até 120 caracteres") String nomeRua,
        @Size(max = 80, message = "bairro aceita até 80 caracteres") String bairro,
        @DecimalMin(value = "-90", message = "latitude deve estar entre -90 e 90") @DecimalMax(value = "90", message = "latitude deve estar entre -90 e 90") BigDecimal latitude,
        @DecimalMin(value = "-180", message = "longitude deve estar entre -180 e 180") @DecimalMax(value = "180", message = "longitude deve estar entre -180 e 180") BigDecimal longitude,
        @NotNull(message = "nivelRisco é obrigatório") @Min(value = 1, message = "nivelRisco deve ser de 1 a 3") @Max(value = 3, message = "nivelRisco deve ser de 1 a 3") Integer nivelRisco,
        Boolean recorrente) {
}
