package com.lsd.logement.mapper;

import com.lsd.logement.dto.PayementDTO;
import com.lsd.logement.entity.finance.Payement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface PayementMapper extends GenericMapper<Payement, PayementDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Payement asEntity(PayementDTO dto);
}