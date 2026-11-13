package br.com.citizentech.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.citizentech.api.model.NivelAlerta;
import br.com.citizentech.api.model.OrigemAlerta;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlertaRequest(
        @NotNull(message = "dataAlerta é obrigatória") LocalDate dataAlerta,
        @NotNull(message = "origem é obrigatória (DEFESA_CIVIL ou MODELO)") OrigemAlerta origem,
        @NotNull(message = "nivel é obrigatório (ATENCAO ou BAIXO_RISCO)") NivelAlerta nivel,
        @DecimalMin(value = "0", message = "probabilidade deve estar entre 0 e 1") @DecimalMax(value = "1", message = "probabilidade deve estar entre 0 e 1") @Digits(integer = 1, fraction = 4, message = "probabilidade aceita até 4 casas decimais") BigDecimal probabilidade,
        @NotBlank(message = "mensagemImpacto é obrigatória") @Size(max = 500, message = "mensagemImpacto aceita até 500 caracteres") String mensagemImpacto) {
}
