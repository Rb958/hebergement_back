package com.lsd.logement.dao;

import com.lsd.logement.entity.stock.FournisseurEntreprise;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurEntrepriseRepository extends PagingAndSortingRepository<FournisseurEntreprise, Integer> {
}