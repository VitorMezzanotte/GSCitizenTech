package br.com.citizentech.api.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.citizentech.api.dao.AlertaDao;
import br.com.citizentech.api.dto.AlertaRequest;
import br.com.citizentech.api.exception.ConflitoException;
import br.com.citizentech.api.exception.RecursoNaoEncontradoException;
import br.com.citizentech.api.exception.RegraNegocioException;
import br.com.citizentech.api.model.Alerta;
import br.com.citizentech.api.model.OrigemAlerta;

/** Regras de negocio dos alertas (oficial da Defesa Civil ou estimado pelo modelo). */
@Service
public class AlertaBO {

    private final AlertaDao dao;

    public AlertaBO(AlertaDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public List<Alerta> listar() {
        return dao.findAllByOrderByDataAlertaDesc();
    }

    @Transactional(readOnly = true)
    public Alerta buscar(Long id) {
        return dao.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Alerta " + id + " não encontrado"));
    }

    @Transactional
    public Alerta criar(AlertaRequest req) {
        validarProbabilidade(req);
        if (dao.existsByDataAlertaAndOrigem(req.dataAlerta(), req.origem())) {
            throw new ConflitoException("Já existe um alerta de origem " + req.origem()
                    + " para a data " + req.dataAlerta());
        }
        Alerta alerta = new Alerta();
        preencher(alerta, req);
        return dao.save(alerta);
    }

    @Transactional
    public Alerta atualizar(Long id, AlertaRequest req) {
        Alerta alerta = buscar(id);
        validarProbabilidade(req);
        if (dao.existsByDataAlertaAndOrigemAndIdNot(req.dataAlerta(), req.origem(), id)) {
            throw new ConflitoException("Já existe outro alerta de origem " + req.origem()
                    + " para a data " + req.dataAlerta());
        }
        preencher(alerta, req);
        return dao.save(alerta);
    }

    @Transactional
    public void excluir(Long id) {
        dao.delete(buscar(id));
    }

    /** Regra do banco: a probabilidade so existe quando a origem e o MODELO. */
    private void validarProbabilidade(AlertaRequest req) {
        if (req.probabilidade() != null && req.origem() != OrigemAlerta.MODELO) {
            throw new RegraNegocioException("A probabilidade só pode ser informada quando a origem é MODELO");
        }
    }

    private void preencher(Alerta alerta, AlertaRequest req) {
        alerta.setDataAlerta(req.dataAlerta());
        alerta.setOrigem(req.origem());
        alerta.setNivel(req.nivel());
        alerta.setProbabilidade(req.probabilidade());
        alerta.setMensagemImpacto(req.mensagemImpacto().trim());
    }
}
