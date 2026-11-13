package br.com.citizentech.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento_alagamento")
public class EventoAlagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @Column(name = "descricao", nullable = false, length = 400)
    private String descricao;

    @Column(name = "fonte_url", length = 500)
    private String fonteUrl;

    @Convert(converter = SimNaoConverter.class)
    @Column(name = "data_confirmada", nullable = false)
    private Boolean dataConfirmada;

    @Column(name = "chuva_dia_mm", precision = 6, scale = 1)
    private BigDecimal chuvaDiaMm;

    @Column(name = "pico_1h_mm", precision = 6, scale = 1)
    private BigDecimal pico1hMm;

    public Long getId() { return id; }
    public LocalDate getDataEvento() { return dataEvento; }
    public String getDescricao() { return descricao; }
    public String getFonteUrl() { return fonteUrl; }
    public Boolean getDataConfirmada() { return dataConfirmada; }
    public BigDecimal getChuvaDiaMm() { return chuvaDiaMm; }
    public BigDecimal getPico1hMm() { return pico1hMm; }
}
