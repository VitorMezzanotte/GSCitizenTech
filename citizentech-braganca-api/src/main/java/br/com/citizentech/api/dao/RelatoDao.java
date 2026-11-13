package br.com.citizentech.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.citizentech.api.model.Relato;

public interface RelatoDao extends JpaRepository<Relato, Long> {

    List<Relato> findAllByOrderByDataRelatoDesc();

    @Query("select r from Relato r where r.ponto.id = :idPonto order by r.dataRelato desc")
    List<Relato> listarPorPonto(@Param("idPonto") Long idPonto);

    @Query("select count(r) from Relato r where r.ponto.id = :idPonto")
    long contarPorPonto(@Param("idPonto") Long idPonto);
}
