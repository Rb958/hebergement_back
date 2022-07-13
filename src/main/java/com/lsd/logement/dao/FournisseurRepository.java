package com.lsd.logement.dao;

import com.lsd.logement.entity.stock.Fournisseur;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends PagingAndSortingRepository<Fournisseur, Integer> {
}