package com.lsd.logement.dao;

import com.lsd.logement.entity.stock.FournisseurParticulier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurParticulierRepository extends PagingAndSortingRepository<FournisseurParticulier, Integer> {
}