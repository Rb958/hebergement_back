package com.lsd.logement.dao;

import com.lsd.logement.entity.client.LocataireSociete;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocataireSocieteRepository extends PagingAndSortingRepository<LocataireSociete, Integer> {
}