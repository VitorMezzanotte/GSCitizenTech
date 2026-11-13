package br.com.citizentech.api.dto;

import br.com.citizentech.api.model.Impacto;

/** Um ponto atingido por determinado evento de alagamento. */
public record PontoAfetadoDTO(Long idPonto, String nomeRua, Impacto impacto) {
}
