package com.lsd.logement.mapper;

import com.lsd.logement.dto.SousCaisseDTO;
import com.lsd.logement.entity.SousCaisse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface SousCaisseMapper extends GenericMapper<SousCaisse, SousCaisseDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    SousCaisse asEntity(SousCaisseDTO dto);
}