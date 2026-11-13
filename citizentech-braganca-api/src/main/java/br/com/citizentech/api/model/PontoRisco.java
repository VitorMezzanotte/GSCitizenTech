package br.com.citizentech.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_risco")
public class PontoRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_rua", nullable = false, length = 120)
    private String nomeRua;

    @Column(name = "bairro", length = 80)
    private String bairro;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "nivel_risco", nullable = false)
    private Integer nivelRisco;

    @Convert(converter = SimNaoConverter.class)
    @Column(name = "recorrente", nullable = false)
    private Boolean recorrente;

    public Long getId() { return id; }
    public String getNomeRua() { return nomeRua; }
    public void setNomeRua(String nomeRua) { this.nomeRua = nomeRua; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public Integer getNivelRisco() { return nivelRisco; }
    public void setNivelRisco(Integer nivelRisco) { this.nivelRisco = nivelRisco; }
    public Boolean getRecorrente() { return recorrente; }
    public void setRecorrente(Boolean recorrente) { this.recorrente = recorrente; }
}
