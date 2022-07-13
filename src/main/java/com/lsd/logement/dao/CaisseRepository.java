package com.lsd.logement.dao;

import com.lsd.logement.entity.finance.Caisse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaisseRepository extends PagingAndSortingRepository<Caisse, Integer> {
}