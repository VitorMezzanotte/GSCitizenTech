package br.com.citizentech.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/** Associativa N:N entre evento de alagamento e ponto de risco. */
@Entity
@Table(name = "evento_ponto")
public class EventoPonto {

    @EmbeddedId
    private EventoPontoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEvento")
    @JoinColumn(name = "id_evento")
    private EventoAlagamento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPonto")
    @JoinColumn(name = "id_ponto")
    private PontoRisco ponto;

    @Enumerated(EnumType.STRING)
    @Column(name = "impacto", nullable = false, length = 15)
    private Impacto impacto;

    public EventoPontoId getId() { return id; }
    public EventoAlagamento getEvento() { return evento; }
    public PontoRisco getPonto() { return ponto; }
    public Impacto getImpacto() { return impacto; }
}
