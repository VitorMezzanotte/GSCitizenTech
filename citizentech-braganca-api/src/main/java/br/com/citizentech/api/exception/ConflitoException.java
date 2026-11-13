package br.com.citizentech.api.exception;

/** Conflito com dados existentes: duplicidade ou vinculo que impede a exclusao (HTTP 409). */
public class ConflitoException extends RuntimeException {
    public ConflitoException(String mensagem) {
        super(mensagem);
    }
}
