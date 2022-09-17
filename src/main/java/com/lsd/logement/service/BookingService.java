package com.lsd.logement.service;

import com.lsd.logement.entity.reservation.Booking;
import com.lsd.logement.entity.finance.Payement;

import java.util.Map;

public interface BookingService extends GenericService<Booking, Integer> {
    Booking save(Booking entity, Integer userId);
    Booking addPayment(Integer userId, Integer bookingId, Payement payement);
    Booking removePayment(Integer userId, Integer bookingId, Payement payement);
    Map<String, Object> bookingStats();
    Booking cancelBooking(Integer bookingId);

    int countAllAvailable();
}