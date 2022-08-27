package com.lsd.logement.dao;

import com.lsd.logement.entity.infra.CategorieEnum;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.infra.TypeLocal;
import com.lsd.logement.entity.reservation.Bail;
import com.lsd.logement.entity.reservation.BookingState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BailRepository extends PagingAndSortingRepository<Bail, Integer> {
    @Query("select count(b.id) as qte from Bail b where b.statut = :status")
    int countBailWithStatus(@Param("status") BookingState status);

    @Query("select distinct l from Local l where l.typeLocal = :typeLocal and l.categorie = :categorie and l not in (select b.local from Bail b where b.validite between :startDate and :endDate)")
    List<Local> findLocalAvailable(@Param("typeLocal") TypeLocal typeLocal, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("categorie") CategorieEnum categorie);

    int countAllByValiditeLessThanEqualAndStatutIsNot(Date now, BookingState state);

    int countAllByValiditeGreaterThanEqualAndStatutIsNot(Date now, BookingState state);

    @Query("select count(b) from Bail b")
    int countAllDueDate(@Param("now") Date now);
}