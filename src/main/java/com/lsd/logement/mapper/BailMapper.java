package com.lsd.logement.mapper;

import com.lsd.logement.dto.BailDTO;
import com.lsd.logement.entity.reservation.Bail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface BailMapper extends GenericMapper<Bail, BailDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Bail asEntity(BailDTO dto);
}