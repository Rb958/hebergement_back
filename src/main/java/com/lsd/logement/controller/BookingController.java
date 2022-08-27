package com.lsd.logement.controller;

import com.lsd.logement.dto.BookingDTO;
import com.lsd.logement.dto.PayementDTO;
import com.lsd.logement.model.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookingController {

    public ResponseEntity<ApiResponse<?>> save(@RequestBody BookingDTO bookingDTO, @PathVariable("userId") int userId);

    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id);

    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id);

    public ResponseEntity<ApiResponse<?>> list();

    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable);

    public ResponseEntity<ApiResponse<?>> update(@RequestBody BookingDTO dto, @PathVariable("id") Integer id);

    public ResponseEntity<ApiResponse<?>> addPayment(@RequestBody PayementDTO dto, @PathVariable("bookingId") Integer id);

    public ResponseEntity<ApiResponse<?>> removePayment(@RequestBody PayementDTO dto, @PathVariable("bookingId") Integer id);

    public ResponseEntity<ApiResponse<?>> cancelBooking(@PathVariable("bookingId") Integer bookingId);

    public ResponseEntity<ApiResponse<?>> bookingState();
}