package com.lsd.logement.entity.mapper;

import com.lsd.logement.entity.dto.DepenseDTO;
import com.lsd.logement.entity.finance.Depense;
import com.lsd.logement.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface DepenseMapper extends GenericMapper<Depense, DepenseDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Depense asEntity(DepenseDTO dto);
}