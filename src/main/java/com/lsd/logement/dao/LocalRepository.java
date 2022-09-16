package com.lsd.logement.dao;

import com.lsd.logement.entity.infra.CategorieEnum;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.reservation.BookingState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface LocalRepository extends PagingAndSortingRepository<Local, Integer> {
    @Query("select l from Local l Where l in (select b.local from Booking b where b.statut = 'CLOTURER' or b.statut = 'ANNULE') or l not in (select b.local from Booking b) order by l.id desc")
    Page<Local> findAllAvailable(Pageable pageable);

    int countAllByCategorie(CategorieEnum categorieEnum);

    @Query("Select l from Local l where l.id = :id and l not in (select b.local from Booking b where b.validite > :startDate or b.statut <> :state) and l not in (select b.local from Bail b where b.validite > :startDate or b.statut <> :state)")
    Optional<Local> localIsFree(@Param("id") Integer id, @Param("startDate") Date startDate, BookingState state);
}