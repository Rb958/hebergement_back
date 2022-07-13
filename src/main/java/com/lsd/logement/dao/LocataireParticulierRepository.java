package com.lsd.logement.dao;

import com.lsd.logement.entity.client.LocataireParticulier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocataireParticulierRepository extends PagingAndSortingRepository<LocataireParticulier, Integer> {
}