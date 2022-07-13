package com.lsd.logement.dao;

import com.lsd.logement.entity.finance.TransfertCaisse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertCaisseRepository extends PagingAndSortingRepository<TransfertCaisse, Integer> {
}