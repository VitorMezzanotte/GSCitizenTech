package br.com.citizentech.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.citizentech.api.model.EventoAlagamento;

public interface EventoAlagamentoDao extends JpaRepository<EventoAlagamento, Long> {

    List<EventoAlagamento> findAllByOrderByDataEventoDesc();
}
