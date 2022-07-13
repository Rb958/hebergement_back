package com.lsd.logement.dao;

import com.lsd.logement.entity.reservation.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {
    @Query("select b.paymentStatus, count(b.id) as qte from Booking b group by b.paymentStatus order by b.paymentStatus asc")
    Map<String, Object> bookingStat();
}