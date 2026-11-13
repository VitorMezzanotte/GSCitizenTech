package br.com.citizentech.api.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/** Converte Boolean (Java) em CHAR(1) 'S'/'N' (Oracle). */
@Converter
public class SimNaoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean valor) {
        if (valor == null) {
            return null;
        }
        return valor ? "S" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return "S".equalsIgnoreCase(valor.trim());
    }
}
