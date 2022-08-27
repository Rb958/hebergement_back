package com.lsd.logement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lsd.logement.entity.finance.Caisse;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class TransactionCaisse implements AbstractEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int amount;
    private String paymentMethod;
    @ManyToOne
    @JsonBackReference("caisse_transactions")
    private Caisse caisse;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public TransactionCaisse() {
        createdAt = ZonedDateTime.now();
        lastUpdatedAt = ZonedDateTime.now();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public ZonedDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    @Override
    public void setLastUpdatedAt(ZonedDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }
}
