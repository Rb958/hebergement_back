package com.lsd.logement.dao;

import com.lsd.logement.entity.SousCaisse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SousCaisseRepository extends PagingAndSortingRepository<SousCaisse, Integer> {
    Optional<SousCaisse> findByNameAndCaisse_Id(String name, Integer caisseId);
}