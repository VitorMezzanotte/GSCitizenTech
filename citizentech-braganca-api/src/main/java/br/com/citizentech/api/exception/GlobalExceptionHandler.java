package br.com.citizentech.api.exception;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.citizentech.api.dto.ErroResposta;

/** Traduz excecoes em respostas JSON padronizadas (400, 404, 409, 500). */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> naoEncontrado(RecursoNaoEncontradoException ex) {
        return montar(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<ErroResposta> conflito(ConflitoException ex) {
        return montar(HttpStatus.CONFLICT, ex.getMessage(), List.of());
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResposta> regraNegocio(RegraNegocioException ex) {
        return montar(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> validacao(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return montar(HttpStatus.BAD_REQUEST, "Dados inválidos na requisição", detalhes);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> corpoIlegivel(HttpMessageNotReadableException ex) {
        return montar(HttpStatus.BAD_REQUEST,
                "Corpo da requisição ilegível ou com valor inválido (verifique JSON, datas no formato AAAA-MM-DD e valores dos enums)",
                List.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResposta> tipoInvalido(MethodArgumentTypeMismatchException ex) {
        return montar(HttpStatus.BAD_REQUEST, "Valor inválido para o parâmetro '" + ex.getName() + "'", List.of());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResposta> integridade(DataIntegrityViolationException ex) {
        log.warn("Violacao de integridade: {}", ex.getMostSpecificCause().getMessage());
        return montar(HttpStatus.CONFLICT,
                "A operação viola uma regra de integridade do banco (duplicidade, vínculo ou valor fora do permitido)",
                List.of());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> inesperado(Exception ex) {
        if (ex instanceof ErrorResponse er) {
            HttpStatus status = HttpStatus.valueOf(er.getStatusCode().value());
            String detalhe = er.getBody().getDetail();
            return montar(status, detalhe != null ? detalhe : status.getReasonPhrase(), List.of());
        }
        log.error("Erro inesperado", ex);
        return montar(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado", List.of());
    }

    private ResponseEntity<ErroResposta> montar(HttpStatus status, String mensagem, List<String> detalhes) {
        ErroResposta corpo = new ErroResposta(OffsetDateTime.now(), status.value(),
                status.getReasonPhrase(), mensagem, detalhes);
        return ResponseEntity.status(status).body(corpo);
    }
}
