package com.lsd.logement.mapper;

import com.lsd.logement.dto.FournisseurDTO;
import com.lsd.logement.entity.stock.Fournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface FournisseurMapper extends GenericMapper<Fournisseur, FournisseurDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Fournisseur asEntity(FournisseurDTO dto);
}