package com.lsd.logement.dao;

import com.lsd.logement.entity.finance.Depense;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepenseRepository extends PagingAndSortingRepository<Depense, Integer> {
}