package com.lsd.logement.mapper;

import com.lsd.logement.dto.LocataireSocieteDTO;
import com.lsd.logement.entity.client.LocataireSociete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface LocataireSocieteMapper extends GenericMapper<LocataireSociete, LocataireSocieteDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    LocataireSociete asEntity(LocataireSocieteDTO dto);
}