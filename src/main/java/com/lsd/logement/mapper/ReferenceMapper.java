package com.lsd.logement.mapper;

import com.lsd.logement.dto.AbstractDTO;
import com.lsd.logement.entity.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;

@Component
public class ReferenceMapper {

    private final EntityManager em;
    Logger logger = LogManager.getLogger(ReferenceMapper.class);

    public ReferenceMapper(EntityManager em) {
        this.em = em;
    }

    @ObjectFactory
    public <T extends AbstractEntity<?>> T resolve(AbstractDTO<?> sourceDTO,
                                                   @TargetType Class<T> type) {
        T entity = null;
        if (sourceDTO.getId() != null) entity = em.find(type, sourceDTO.getId());
        try {
            if (entity == null) {
                entity = type.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage());
        }
        return entity;
    }
}