package br.com.citizentech.api.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.citizentech.api.model.Alerta;
import br.com.citizentech.api.model.OrigemAlerta;

public interface AlertaDao extends JpaRepository<Alerta, Long> {

    List<Alerta> findAllByOrderByDataAlertaDesc();

    boolean existsByDataAlertaAndOrigem(LocalDate dataAlerta, OrigemAlerta origem);

    boolean existsByDataAlertaAndOrigemAndIdNot(LocalDate dataAlerta, OrigemAlerta origem, Long id);
}
