package br.com.citizentech.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_alerta", nullable = false)
    private LocalDate dataAlerta;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem", nullable = false, length = 12)
    private OrigemAlerta origem;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false, length = 12)
    private NivelAlerta nivel;

    @Column(name = "probabilidade", precision = 5, scale = 4)
    private BigDecimal probabilidade;

    @Column(name = "mensagem_impacto", nullable = false, length = 500)
    private String mensagemImpacto;

    public Long getId() { return id; }
    public LocalDate getDataAlerta() { return dataAlerta; }
    public void setDataAlerta(LocalDate dataAlerta) { this.dataAlerta = dataAlerta; }
    public OrigemAlerta getOrigem() { return origem; }
    public void setOrigem(OrigemAlerta origem) { this.origem = origem; }
    public NivelAlerta getNivel() { return nivel; }
    public void setNivel(NivelAlerta nivel) { this.nivel = nivel; }
    public BigDecimal getProbabilidade() { return probabilidade; }
    public void setProbabilidade(BigDecimal probabilidade) { this.probabilidade = probabilidade; }
    public String getMensagemImpacto() { return mensagemImpacto; }
    public void setMensagemImpacto(String mensagemImpacto) { this.mensagemImpacto = mensagemImpacto; }
}
