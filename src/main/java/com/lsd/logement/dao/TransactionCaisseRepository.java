package com.lsd.logement.dao;

import com.lsd.logement.entity.TransactionCaisse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCaisseRepository extends PagingAndSortingRepository<TransactionCaisse, Integer> {
}