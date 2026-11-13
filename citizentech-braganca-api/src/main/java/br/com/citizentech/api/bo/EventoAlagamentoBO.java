package br.com.citizentech.api.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.citizentech.api.dao.EventoAlagamentoDao;
import br.com.citizentech.api.dao.EventoPontoDao;
import br.com.citizentech.api.dto.PontoAfetadoDTO;
import br.com.citizentech.api.exception.RecursoNaoEncontradoException;
import br.com.citizentech.api.model.EventoAlagamento;

/** Consultas de eventos de alagamento (somente leitura). */
@Service
public class EventoAlagamentoBO {

    private final EventoAlagamentoDao dao;
    private final EventoPontoDao eventoPontoDao;

    public EventoAlagamentoBO(EventoAlagamentoDao dao, EventoPontoDao eventoPontoDao) {
        this.dao = dao;
        this.eventoPontoDao = eventoPontoDao;
    }

    @Transactional(readOnly = true)
    public List<EventoAlagamento> listar() {
        return dao.findAllByOrderByDataEventoDesc();
    }

    @Transactional(readOnly = true)
    public EventoAlagamento buscar(Long id) {
        return dao.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Evento de alagamento " + id + " não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<PontoAfetadoDTO> pontosAfetados(Long id) {
        buscar(id);
        return eventoPontoDao.pontosDoEvento(id);
    }
}
