package br.com.citizentech.api.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.citizentech.api.dao.RelatoDao;
import br.com.citizentech.api.dto.RelatoRequest;
import br.com.citizentech.api.exception.RecursoNaoEncontradoException;
import br.com.citizentech.api.model.OrigemRelato;
import br.com.citizentech.api.model.PontoRisco;
import br.com.citizentech.api.model.Relato;

/** Regras de negocio dos relatos de cidadaos. */
@Service
public class RelatoBO {

    private final RelatoDao dao;
    private final PontoRiscoBO pontoBO;

    public RelatoBO(RelatoDao dao, PontoRiscoBO pontoBO) {
        this.dao = dao;
        this.pontoBO = pontoBO;
    }

    @Transactional(readOnly = true)
    public List<Relato> listar(Long idPonto) {
        if (idPonto == null) {
            return dao.findAllByOrderByDataRelatoDesc();
        }
        pontoBO.buscar(idPonto);
        return dao.listarPorPonto(idPonto);
    }

    @Transactional(readOnly = true)
    public Relato buscar(Long id) {
        return dao.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Relato " + id + " não encontrado"));
    }

    @Transactional
    public Relato criar(RelatoRequest req) {
        Relato relato = new Relato();
        preencher(relato, req);
        return dao.save(relato);
    }

    @Transactional
    public Relato atualizar(Long id, RelatoRequest req) {
        Relato relato = buscar(id);
        preencher(relato, req);
        return dao.save(relato);
    }

    @Transactional
    public void excluir(Long id) {
        dao.delete(buscar(id));
    }

    private void preencher(Relato relato, RelatoRequest req) {
        PontoRisco ponto = pontoBO.buscar(req.idPonto());
        relato.setPonto(ponto);
        relato.setDataRelato(req.dataRelato());
        relato.setTexto(req.texto().trim());
        // Nesta fase do projeto os relatos sao ficticios; so e CIDADAO se informado.
        relato.setOrigem(req.origem() != null ? req.origem() : OrigemRelato.FICTICIO);
    }
}
