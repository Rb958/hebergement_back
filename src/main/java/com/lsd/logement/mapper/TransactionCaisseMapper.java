package com.lsd.logement.mapper;

import com.lsd.logement.dto.TransactionCaisseDTO;
import com.lsd.logement.entity.TransactionCaisse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface TransactionCaisseMapper extends GenericMapper<TransactionCaisse, TransactionCaisseDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    TransactionCaisse asEntity(TransactionCaisseDTO dto);
}