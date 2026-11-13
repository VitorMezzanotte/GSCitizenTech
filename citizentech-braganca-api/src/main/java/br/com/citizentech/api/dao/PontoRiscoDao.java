package br.com.citizentech.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.citizentech.api.model.PontoRisco;

public interface PontoRiscoDao extends JpaRepository<PontoRisco, Long> {

    List<PontoRisco> findAllByOrderByNivelRiscoDescNomeRuaAsc();

    boolean existsByNomeRua(String nomeRua);

    boolean existsByNomeRuaAndIdNot(String nomeRua, Long id);
}
