package com.lsd.logement.entity.finance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lsd.logement.entity.AbstractEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class TransfertCaisse implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transactionRef;
    @ManyToOne
    @JsonBackReference("caisse_transfert_de")
    private Caisse transfertDe;
    @ManyToOne
    @JsonBackReference("caisse_transfert_a")
    private Caisse transfertA;
    private int montant;
    @Enumerated(EnumType.STRING)
    private StatutTransfert statut;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public TransfertCaisse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public Caisse getTransfertDe() {
        return transfertDe;
    }

    public void setTransfertDe(Caisse transfertDe) {
        this.transfertDe = transfertDe;
    }

    public Caisse getTransfertA() {
        return transfertA;
    }

    public void setTransfertA(Caisse transfertA) {
        this.transfertA = transfertA;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public StatutTransfert getStatut() {
        return statut;
    }

    public void setStatut(StatutTransfert statut) {
        this.statut = statut;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(ZonedDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
