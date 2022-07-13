package com.lsd.logement.dao;

import com.lsd.logement.entity.finance.Payement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayementRepository extends PagingAndSortingRepository<Payement, Integer> {
}