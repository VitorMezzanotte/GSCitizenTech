package br.com.citizentech.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.citizentech.api.bo.AlertaBO;
import br.com.citizentech.api.dto.AlertaRequest;
import br.com.citizentech.api.model.Alerta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alertas")
@Tag(name = "Alertas", description = "Alerta do dia (Defesa Civil ou modelo) com a mensagem de impacto por rua")
public class AlertaController {

    private final AlertaBO bo;

    public AlertaController(AlertaBO bo) {
        this.bo = bo;
    }

    @GetMapping
    public List<Alerta> listar() {
        return bo.listar();
    }

    @GetMapping("/{id}")
    public Alerta buscar(@PathVariable Long id) {
        return bo.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alerta criar(@Valid @RequestBody AlertaRequest req) {
        return bo.criar(req);
    }

    @PutMapping("/{id}")
    public Alerta atualizar(@PathVariable Long id, @Valid @RequestBody AlertaRequest req) {
        return bo.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        bo.excluir(id);
    }
}
