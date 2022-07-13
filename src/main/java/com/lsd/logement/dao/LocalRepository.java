package com.lsd.logement.dao;

import com.lsd.logement.entity.infra.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends PagingAndSortingRepository<Local, Integer> {
    @Query("select l from Local l Where l in (select b.local from Booking b where b.statut = 'CLOTURER' or b.statut = 'ANNULE') or l not in (select b.local from Booking b) order by l.id desc")
    Page<Local> findAllAvailable(Pageable pageable);
}