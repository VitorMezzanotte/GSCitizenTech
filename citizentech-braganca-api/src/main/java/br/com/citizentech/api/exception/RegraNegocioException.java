package br.com.citizentech.api.exception;

/** Violacao de regra de negocio (HTTP 400). */
public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
