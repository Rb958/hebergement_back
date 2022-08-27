package com.lsd.logement.dto;

import com.lsd.logement.entity.reservation.Bail;
import com.lsd.logement.entity.reservation.Booking;

import java.time.ZonedDateTime;

public class PayementDTO extends AbstractDTO<Integer> {
    private Integer id;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    private String paymentMethod;
    private int amount;
    private boolean isLast;
    private int rest;
    private int discount;
    private Booking booking;
    private Bail bail;

    public PayementDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setCreatedAt(java.time.ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setLastUpdatedAt(java.time.ZonedDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public java.time.ZonedDateTime getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public boolean getIsLast() {
        return this.isLast;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getRest() {
        return this.rest;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Bail getBail() {
        return bail;
    }

    public void setBail(Bail bail) {
        this.bail = bail;
    }
}