package com.lsd.logement.dao;

import com.lsd.logement.entity.stock.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, Integer> {
}