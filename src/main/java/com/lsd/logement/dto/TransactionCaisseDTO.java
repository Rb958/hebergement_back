package com.lsd.logement.dto;

import com.lsd.logement.entity.finance.Caisse;
import java.time.ZonedDateTime;

public class TransactionCaisseDTO extends AbstractDTO<Integer> {
    private Integer id;
    private int amount;
    private String paymentMethod;
    private Caisse caisse;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public TransactionCaisseDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public Caisse getCaisse() {
        return this.caisse;
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
}
