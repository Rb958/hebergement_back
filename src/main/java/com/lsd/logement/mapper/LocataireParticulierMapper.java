package com.lsd.logement.mapper;

import com.lsd.logement.dto.LocataireParticulierDTO;
import com.lsd.logement.entity.client.LocataireParticulier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface LocataireParticulierMapper extends GenericMapper<LocataireParticulier, LocataireParticulierDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    LocataireParticulier asEntity(LocataireParticulierDTO dto);
}