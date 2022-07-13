package com.lsd.logement.dao;

import com.lsd.logement.entity.stock.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {
}