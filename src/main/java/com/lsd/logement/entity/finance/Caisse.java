package com.lsd.logement.entity.finance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lsd.logement.entity.AbstractEntity;
import com.lsd.logement.entity.finance.StatutCaisse;
import com.lsd.logement.entity.personnel.User;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Caisse implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String ref;
    @Enumerated(EnumType.STRING)
    private StatutCaisse status;
    private int solde;
    @ManyToOne()
    @JsonBackReference("caisse_user")
    private User createdBy;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public Caisse() {}

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
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

    public StatutCaisse getStatus() {
        return status;
    }

    public void setStatus(StatutCaisse status) {
        this.status = status;
    }
}
