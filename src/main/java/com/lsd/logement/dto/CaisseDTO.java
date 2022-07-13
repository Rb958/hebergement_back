package com.lsd.logement.dto;

import com.lsd.logement.entity.finance.StatutCaisse;
import com.lsd.logement.entity.personnel.User;

public class CaisseDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String nom;
    private String ref;
    private StatutCaisse status;
    private int solde;
    private User createdBy;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public CaisseDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return this.ref;
    }

    public void setStatus(StatutCaisse status) {
        this.status = status;
    }

    public StatutCaisse getStatus() {
        return this.status;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public int getSolde() {
        return this.solde;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getCreatedBy() {
        return this.createdBy;
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