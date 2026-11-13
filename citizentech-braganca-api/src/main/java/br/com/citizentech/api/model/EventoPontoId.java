package br.com.citizentech.api.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/** Chave composta de EVENTO_PONTO (id_evento, id_ponto). */
@Embeddable
public class EventoPontoId implements Serializable {

    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "id_ponto")
    private Long idPonto;

    public EventoPontoId() { }

    public EventoPontoId(Long idEvento, Long idPonto) {
        this.idEvento = idEvento;
        this.idPonto = idPonto;
    }

    public Long getIdEvento() { return idEvento; }
    public Long getIdPonto() { return idPonto; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventoPontoId)) return false;
        EventoPontoId outro = (EventoPontoId) o;
        return Objects.equals(idEvento, outro.idEvento) && Objects.equals(idPonto, outro.idPonto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, idPonto);
    }
}
