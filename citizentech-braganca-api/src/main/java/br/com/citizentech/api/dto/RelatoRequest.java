package br.com.citizentech.api.dto;

import java.time.LocalDate;

import br.com.citizentech.api.model.OrigemRelato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record RelatoRequest(
        @NotNull(message = "idPonto é obrigatório") Long idPonto,
        @NotNull(message = "dataRelato é obrigatória") @PastOrPresent(message = "dataRelato não pode ser futura") LocalDate dataRelato,
        @NotBlank(message = "texto é obrigatório") @Size(max = 500, message = "texto aceita até 500 caracteres") String texto,
        OrigemRelato origem) {
}
