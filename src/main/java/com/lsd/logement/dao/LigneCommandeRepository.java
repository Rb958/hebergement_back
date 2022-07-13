package com.lsd.logement.dao;

import com.lsd.logement.entity.stock.LigneCommande;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends PagingAndSortingRepository<LigneCommande, Integer> {
}