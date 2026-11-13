package br.com.citizentech.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.citizentech.api.bo.EventoAlagamentoBO;
import br.com.citizentech.api.dto.PontoAfetadoDTO;
import br.com.citizentech.api.model.EventoAlagamento;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/eventos")
@Tag(name = "Eventos de alagamento", description = "Histórico 2022-2026 (somente leitura)")
public class EventoAlagamentoController {

    private final EventoAlagamentoBO bo;

    public EventoAlagamentoController(EventoAlagamentoBO bo) {
        this.bo = bo;
    }

    @GetMapping
    public List<EventoAlagamento> listar() {
        return bo.listar();
    }

    @GetMapping("/{id}")
    public EventoAlagamento buscar(@PathVariable Long id) {
        return bo.buscar(id);
    }

    @GetMapping("/{id}/pontos")
    public List<PontoAfetadoDTO> pontosAfetados(@PathVariable Long id) {
        return bo.pontosAfetados(id);
    }
}
