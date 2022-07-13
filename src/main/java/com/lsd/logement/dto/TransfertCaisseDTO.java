package com.lsd.logement.dto;

import com.lsd.logement.entity.finance.Caisse;
import com.lsd.logement.entity.finance.StatutTransfert;

import java.time.ZonedDateTime;

public class TransfertCaisseDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String transactionRef;
    private Caisse transfertDe;
    private Caisse transfertA;
    private int montant;
    private StatutTransfert statut;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public TransfertCaisseDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public String getTransactionRef() {
        return this.transactionRef;
    }

    public void setTransfertDe(Caisse transfertDe) {
        this.transfertDe = transfertDe;
    }

    public Caisse getTransfertDe() {
        return this.transfertDe;
    }

    public void setTransfertA(Caisse transfertA) {
        this.transfertA = transfertA;
    }

    public Caisse getTransfertA() {
        return this.transfertA;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getMontant() {
        return this.montant;
    }

    public void setStatut(StatutTransfert statut) {
        this.statut = statut;
    }

    public StatutTransfert getStatut() {
        return this.statut;
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