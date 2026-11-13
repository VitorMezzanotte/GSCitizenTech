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

import br.com.citizentech.api.bo.PontoRiscoBO;
import br.com.citizentech.api.dto.HistoricoPontoDTO;
import br.com.citizentech.api.dto.PontoRiscoRequest;
import br.com.citizentech.api.model.PontoRisco;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pontos")
@Tag(name = "Pontos de risco", description = "Ruas do centro com histórico de alagamento")
public class PontoRiscoController {

    private final PontoRiscoBO bo;

    public PontoRiscoController(PontoRiscoBO bo) {
        this.bo = bo;
    }

    @GetMapping
    public List<PontoRisco> listar() {
        return bo.listar();
    }

    @GetMapping("/{id}")
    public PontoRisco buscar(@PathVariable Long id) {
        return bo.buscar(id);
    }

    @GetMapping("/{id}/historico")
    public List<HistoricoPontoDTO> historico(@PathVariable Long id) {
        return bo.historico(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PontoRisco criar(@Valid @RequestBody PontoRiscoRequest req) {
        return bo.criar(req);
    }

    @PutMapping("/{id}")
    public PontoRisco atualizar(@PathVariable Long id, @Valid @RequestBody PontoRiscoRequest req) {
        return bo.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        bo.excluir(id);
    }
}
