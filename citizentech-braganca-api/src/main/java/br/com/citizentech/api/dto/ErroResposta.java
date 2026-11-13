package br.com.citizentech.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

/** Corpo padrao de erro devolvido pela API. */
public record ErroResposta(
        OffsetDateTime timestamp,
        int status,
        String erro,
        String mensagem,
        List<String> detalhes) {
}
