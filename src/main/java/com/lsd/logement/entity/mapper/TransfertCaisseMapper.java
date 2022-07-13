package com.lsd.logement.entity.mapper;

import com.lsd.logement.entity.dto.TransfertCaisseDTO;
import com.lsd.logement.entity.finance.TransfertCaisse;
import com.lsd.logement.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface TransfertCaisseMapper extends GenericMapper<TransfertCaisse, TransfertCaisseDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    TransfertCaisse asEntity(TransfertCaisseDTO dto);
}