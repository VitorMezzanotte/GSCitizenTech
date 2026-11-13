package br.com.citizentech.api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Teste de vida da API e da conexao com o Oracle da FIAP. */
@RestController
public class HealthController {

    private final JdbcTemplate jdbc;

    public HealthController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("api", "CitizenTech Braganca");
        try {
            jdbc.queryForObject("SELECT 1 FROM DUAL", Integer.class);
            Integer pontos = jdbc.queryForObject("SELECT COUNT(*) FROM ponto_risco", Integer.class);
            body.put("banco", "OK");
            body.put("pontos_risco", pontos);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("banco", "FALHA");
            body.put("erro", e.getClass().getSimpleName() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
        }
    }
}
