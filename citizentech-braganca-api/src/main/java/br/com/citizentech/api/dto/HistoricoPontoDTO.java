package br.com.citizentech.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.citizentech.api.model.Impacto;

/** Um evento de alagamento que atingiu determinado ponto (historico por rua). */
public record HistoricoPontoDTO(
        Long idEvento,
        LocalDate dataEvento,
        String descricao,
        BigDecimal chuvaDiaMm,
        BigDecimal pico1hMm,
        Impacto impacto) {
}
