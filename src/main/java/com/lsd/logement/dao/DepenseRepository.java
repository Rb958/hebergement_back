package com.lsd.logement.dao;

import com.lsd.logement.entity.finance.Depense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface DepenseRepository extends PagingAndSortingRepository<Depense, Integer> {
    @Query("select sum(d.montant) from Depense d where d.createdAt between :startDate and :endDate")
    Optional<Long> getDepenseOfMonth(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);
}