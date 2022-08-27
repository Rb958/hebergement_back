package com.lsd.logement.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsd.logement.entity.infra.Immobilisation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.AttributeConverter;
import java.util.List;

public class ImmobilisationConverter implements AttributeConverter<List<Immobilisation>, String> {

    private static ObjectMapper mapper;
    Logger logger = LogManager.getLogger(ImmobilisationConverter.class);

    static {
        mapper = new ObjectMapper();
    }
    @Override
    public String convertToDatabaseColumn(List<Immobilisation> responses) {
        try {
            return mapper.writeValueAsString(responses);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Immobilisation> convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, new TypeReference<List<Immobilisation>>() {});
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
