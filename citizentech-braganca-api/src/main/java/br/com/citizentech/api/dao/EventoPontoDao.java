package br.com.citizentech.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.citizentech.api.dto.HistoricoPontoDTO;
import br.com.citizentech.api.dto.PontoAfetadoDTO;
import br.com.citizentech.api.model.EventoPonto;
import br.com.citizentech.api.model.EventoPontoId;

public interface EventoPontoDao extends JpaRepository<EventoPonto, EventoPontoId> {

    @Query("select count(ep) from EventoPonto ep where ep.ponto.id = :idPonto")
    long contarPorPonto(@Param("idPonto") Long idPonto);

    @Query("select new br.com.citizentech.api.dto.HistoricoPontoDTO("
            + "e.id, e.dataEvento, e.descricao, e.chuvaDiaMm, e.pico1hMm, ep.impacto) "
            + "from EventoPonto ep join ep.evento e "
            + "where ep.ponto.id = :idPonto order by e.dataEvento desc")
    List<HistoricoPontoDTO> historicoDoPonto(@Param("idPonto") Long idPonto);

    @Query("select new br.com.citizentech.api.dto.PontoAfetadoDTO(p.id, p.nomeRua, ep.impacto) "
            + "from EventoPonto ep join ep.ponto p "
            + "where ep.evento.id = :idEvento order by p.nomeRua")
    List<PontoAfetadoDTO> pontosDoEvento(@Param("idEvento") Long idEvento);
}
