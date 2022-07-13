package com.lsd.logement.dao;

import com.lsd.logement.entity.stock.Commande;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends PagingAndSortingRepository<Commande, Integer> {
}