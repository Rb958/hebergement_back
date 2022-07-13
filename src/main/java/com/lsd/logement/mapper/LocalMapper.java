package com.lsd.logement.mapper;

import com.lsd.logement.dto.LocalDTO;
import com.lsd.logement.entity.infra.Local;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface LocalMapper extends GenericMapper<Local, LocalDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Local asEntity(LocalDTO dto);
}