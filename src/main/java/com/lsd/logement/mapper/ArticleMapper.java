package com.lsd.logement.mapper;

import com.lsd.logement.dto.ArticleDTO;
import com.lsd.logement.entity.stock.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface ArticleMapper extends GenericMapper<Article, ArticleDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Article asEntity(ArticleDTO dto);
}