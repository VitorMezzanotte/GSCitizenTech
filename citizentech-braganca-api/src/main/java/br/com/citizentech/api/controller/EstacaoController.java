package br.com.citizentech.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.citizentech.api.bo.EstacaoBO;
import br.com.citizentech.api.model.Estacao;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/estacoes")
@Tag(name = "Estações", description = "Estação INMET A744 e sensores (somente leitura)")
public class EstacaoController {

    private final EstacaoBO bo;

    public EstacaoController(EstacaoBO bo) {
        this.bo = bo;
    }

    @GetMapping
    public List<Estacao> listar() {
        return bo.listar();
    }

    @GetMapping("/{id}")
    public Estacao buscar(@PathVariable Long id) {
        return bo.buscar(id);
    }
}
