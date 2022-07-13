package com.lsd.logement.mapper;

import com.lsd.logement.dto.LigneCommandeDTO;
import com.lsd.logement.entity.stock.LigneCommande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface LigneCommandeMapper extends GenericMapper<LigneCommande, LigneCommandeDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    LigneCommande asEntity(LigneCommandeDTO dto);
}