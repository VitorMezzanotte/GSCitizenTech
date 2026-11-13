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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.citizentech.api.bo.RelatoBO;
import br.com.citizentech.api.dto.RelatoRequest;
import br.com.citizentech.api.model.Relato;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/relatos")
@Tag(name = "Relatos", description = "Relatos colaborativos (fictícios nesta fase)")
public class RelatoController {

    private final RelatoBO bo;

    public RelatoController(RelatoBO bo) {
        this.bo = bo;
    }

    @GetMapping
    public List<Relato> listar(@RequestParam(required = false) Long idPonto) {
        return bo.listar(idPonto);
    }

    @GetMapping("/{id}")
    public Relato buscar(@PathVariable Long id) {
        return bo.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Relato criar(@Valid @RequestBody RelatoRequest req) {
        return bo.criar(req);
    }

    @PutMapping("/{id}")
    public Relato atualizar(@PathVariable Long id, @Valid @RequestBody RelatoRequest req) {
        return bo.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        bo.excluir(id);
    }
}
