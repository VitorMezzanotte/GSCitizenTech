package br.com.citizentech.api.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.citizentech.api.dao.EstacaoDao;
import br.com.citizentech.api.exception.RecursoNaoEncontradoException;
import br.com.citizentech.api.model.Estacao;

/** Consultas de estacoes e sensores (somente leitura). */
@Service
public class EstacaoBO {

    private final EstacaoDao dao;

    public EstacaoBO(EstacaoDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public List<Estacao> listar() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Estacao buscar(Long id) {
        return dao.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Estação " + id + " não encontrada"));
    }
}
