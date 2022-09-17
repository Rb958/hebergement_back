package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.BookingController;
import com.lsd.logement.dto.BookingDTO;
import com.lsd.logement.dto.PayementDTO;
import com.lsd.logement.entity.reservation.Booking;
import com.lsd.logement.mapper.BookingMapper;
import com.lsd.logement.mapper.PayementMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/booking")
@RestController
public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final PayementMapper payementMapper;

    public BookingControllerImpl(BookingService bookingService, BookingMapper bookingMapper, PayementMapper payementMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.payementMapper = payementMapper;
    }

    @Override
    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> save(@RequestBody BookingDTO bookingDTO, @PathVariable("userId") int userId) {
        try {
            Booking booking = bookingMapper.asEntity(bookingDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingMapper.asDTO(bookingService.save(booking, userId)))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            Booking booking = bookingService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingMapper.asDTO(booking))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            bookingService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(200, "Supprimer avec succes")
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingMapper.asDTOList(bookingService.findAll()))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<Booking> bookingPage = bookingService.findAll(pageable);
            List<BookingDTO> dtoList = bookingPage
                    .stream()
                    .map(bookingMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, bookingPage.getTotalElements()))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody BookingDTO bookingDTO, @PathVariable("id") Integer id) {
        try {
            Booking booking = bookingMapper.asEntity(bookingDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingMapper.asDTO(bookingService.update(booking, id)))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @PatchMapping("/user/{userId}/payment/add/{id}")
    @Override
    public ResponseEntity<ApiResponse<?>> addPayment(Integer userId, PayementDTO paymentDto, Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingMapper.asDTO(bookingService.addPayment(userId, id, payementMapper.asEntity(paymentDto))))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PatchMapping("/user/{userId}/payment/remove/{id}")
    public ResponseEntity<ApiResponse<?>> removePayment(Integer userId, PayementDTO dto, Integer id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingMapper.asDTO(bookingService.removePayment(userId, id, payementMapper.asEntity(dto))))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelBooking(Integer bookingId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingMapper.asDTO(bookingService.cancelBooking(bookingId)))
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<?>> bookingState() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(bookingService.bookingStats())
            );
        }catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}