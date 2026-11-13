package br.com.citizentech.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "relato")
public class Relato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ponto", nullable = false)
    private PontoRisco ponto;

    @Column(name = "data_relato", nullable = false)
    private LocalDate dataRelato;

    @Column(name = "texto", nullable = false, length = 500)
    private String texto;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem", nullable = false, length = 10)
    private OrigemRelato origem;

    public Long getId() { return id; }
    public PontoRisco getPonto() { return ponto; }
    public void setPonto(PontoRisco ponto) { this.ponto = ponto; }
    public LocalDate getDataRelato() { return dataRelato; }
    public void setDataRelato(LocalDate dataRelato) { this.dataRelato = dataRelato; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public OrigemRelato getOrigem() { return origem; }
    public void setOrigem(OrigemRelato origem) { this.origem = origem; }

    @JsonProperty("idPonto")
    public Long getIdPonto() {
        return ponto != null ? ponto.getId() : null;
    }

    @JsonProperty("nomeRua")
    public String getNomeRua() {
        return ponto != null ? ponto.getNomeRua() : null;
    }
}
