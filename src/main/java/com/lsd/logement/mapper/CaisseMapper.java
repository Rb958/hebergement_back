package com.lsd.logement.mapper;

import com.lsd.logement.dto.CaisseDTO;
import com.lsd.logement.entity.finance.Caisse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface CaisseMapper extends GenericMapper<Caisse, CaisseDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Caisse asEntity(CaisseDTO dto);
}