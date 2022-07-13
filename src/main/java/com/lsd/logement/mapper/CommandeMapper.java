package com.lsd.logement.mapper;

import com.lsd.logement.dto.CommandeDTO;
import com.lsd.logement.entity.stock.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface CommandeMapper extends GenericMapper<Commande, CommandeDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Commande asEntity(CommandeDTO dto);
}