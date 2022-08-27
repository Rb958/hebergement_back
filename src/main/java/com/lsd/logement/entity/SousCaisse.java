package com.lsd.logement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lsd.logement.entity.finance.Caisse;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class SousCaisse implements AbstractEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int soldeInit;
    private int total;
    private int soldeFin;
    private int diff;
    @ManyToOne
    @JsonBackReference("sous_caisse")
    private Caisse caisse;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public SousCaisse(){}

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoldeInit() {
        return soldeInit;
    }

    public void setSoldeInit(int soldeInit) {
        this.soldeInit = soldeInit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSoldeFin() {
        return soldeFin;
    }

    public void setSoldeFin(int soldeFin) {
        this.soldeFin = soldeFin;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
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

    public void add(int amount) {
        this.total += amount;
    }
}
