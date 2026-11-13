package br.com.citizentech.api.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.citizentech.api.dao.EventoPontoDao;
import br.com.citizentech.api.dao.PontoRiscoDao;
import br.com.citizentech.api.dao.RelatoDao;
import br.com.citizentech.api.dto.HistoricoPontoDTO;
import br.com.citizentech.api.dto.PontoRiscoRequest;
import br.com.citizentech.api.exception.ConflitoException;
import br.com.citizentech.api.exception.RecursoNaoEncontradoException;
import br.com.citizentech.api.model.PontoRisco;

/** Regras de negocio dos pontos de risco. */
@Service
public class PontoRiscoBO {

    private final PontoRiscoDao dao;
    private final RelatoDao relatoDao;
    private final EventoPontoDao eventoPontoDao;

    public PontoRiscoBO(PontoRiscoDao dao, RelatoDao relatoDao, EventoPontoDao eventoPontoDao) {
        this.dao = dao;
        this.relatoDao = relatoDao;
        this.eventoPontoDao = eventoPontoDao;
    }

    @Transactional(readOnly = true)
    public List<PontoRisco> listar() {
        return dao.findAllByOrderByNivelRiscoDescNomeRuaAsc();
    }

    @Transactional(readOnly = true)
    public PontoRisco buscar(Long id) {
        return dao.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Ponto de risco " + id + " não encontrado"));
    }

    /** Eventos de alagamento que atingiram o ponto (impacto historico por rua). */
    @Transactional(readOnly = true)
    public List<HistoricoPontoDTO> historico(Long id) {
        buscar(id);
        return eventoPontoDao.historicoDoPonto(id);
    }

    @Transactional
    public PontoRisco criar(PontoRiscoRequest req) {
        String nome = req.nomeRua().trim();
        if (dao.existsByNomeRua(nome)) {
            throw new ConflitoException("Já existe um ponto de risco com o nome de rua '" + nome + "'");
        }
        PontoRisco ponto = new PontoRisco();
        preencher(ponto, req, nome);
        return dao.save(ponto);
    }

    @Transactional
    public PontoRisco atualizar(Long id, PontoRiscoRequest req) {
        PontoRisco ponto = buscar(id);
        String nome = req.nomeRua().trim();
        if (dao.existsByNomeRuaAndIdNot(nome, id)) {
            throw new ConflitoException("Já existe outro ponto de risco com o nome de rua '" + nome + "'");
        }
        preencher(ponto, req, nome);
        return dao.save(ponto);
    }

    @Transactional
    public void excluir(Long id) {
        PontoRisco ponto = buscar(id);
        if (relatoDao.contarPorPonto(id) > 0) {
            throw new ConflitoException("O ponto possui relatos vinculados; exclua os relatos antes");
        }
        if (eventoPontoDao.contarPorPonto(id) > 0) {
            throw new ConflitoException("O ponto está vinculado a eventos de alagamento e não pode ser excluído");
        }
        dao.delete(ponto);
    }

    private void preencher(PontoRisco ponto, PontoRiscoRequest req, String nomeRua) {
        ponto.setNomeRua(nomeRua);
        ponto.setBairro(req.bairro());
        ponto.setLatitude(req.latitude());
        ponto.setLongitude(req.longitude());
        ponto.setNivelRisco(req.nivelRisco());
        ponto.setRecorrente(req.recorrente() != null ? req.recorrente() : Boolean.FALSE);
    }
}
