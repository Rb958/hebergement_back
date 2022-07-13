package com.lsd.logement.mapper;

import com.lsd.logement.dto.StockDTO;
import com.lsd.logement.entity.stock.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface StockMapper extends GenericMapper<Stock, StockDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Stock asEntity(StockDTO dto);
}