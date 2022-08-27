package com.lsd.logement.dao;

import com.lsd.logement.entity.infra.CategorieEnum;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.infra.TypeLocal;
import com.lsd.logement.entity.reservation.Booking;
import com.lsd.logement.entity.reservation.BookingState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {
    @Query("select count(b.id) as qte from Booking b where b.statut = :status")
    int countBookingWithStatus(@Param("status")BookingState status);

    @Query("select distinct l from Local l where l.typeLocal = :typeLocal and l.categorie = :categorie and l not in (select b.local from Booking b where b.validite > :startDate or b.statut <> :state) and l not in (select b.local from Bail b where b.validite > :startDate or b.statut <> :state)")
    List<Local> findLocalAvailable(@Param("typeLocal") TypeLocal typeLocal, @Param("startDate") Date startDate, @Param("categorie") CategorieEnum categorie, BookingState state);

    int countAllByValiditeGreaterThanEqualAndStatutIsNot(Date date, BookingState cloturer);
}