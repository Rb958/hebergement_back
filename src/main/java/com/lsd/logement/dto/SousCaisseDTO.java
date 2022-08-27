package com.lsd.logement.dto;

import com.lsd.logement.entity.finance.Caisse;

import java.time.ZonedDateTime;

public class SousCaisseDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String name;
    private int soldeInit;
    private int total;
    private int soldeFin;
    private int diff;
    private Caisse caisse;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public SousCaisseDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSoldeInit(int soldeInit) {
        this.soldeInit = soldeInit;
    }

    public int getSoldeInit() {
        return this.soldeInit;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setSoldeFin(int soldeFin) {
        this.soldeFin = soldeFin;
    }

    public int getSoldeFin() {
        return this.soldeFin;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getDiff() {
        return this.diff;
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