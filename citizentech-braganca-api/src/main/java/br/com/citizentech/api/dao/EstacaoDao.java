package br.com.citizentech.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.citizentech.api.model.Estacao;

public interface EstacaoDao extends JpaRepository<Estacao, Long> {
}
