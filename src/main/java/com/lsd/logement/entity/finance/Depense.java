package com.lsd.logement.entity.finance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lsd.logement.entity.AbstractEntity;
import com.lsd.logement.entity.infra.Local;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Depense implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String demandeur;
    @ManyToOne()
    @JsonBackReference("depense_local")
    private Local local;
    private String categorie;
    @Enumerated(EnumType.STRING)
    private StatutDepense status;
    private String srcDepense;
    private String type;
    private int montant;
    private String validatedBy;
    @Column(columnDefinition = "TEXT")
    private String commentaire;
    private String pieceJointe;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public Depense() { }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(String pieceJointe) {
        this.pieceJointe = pieceJointe;
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

    public StatutDepense getStatus() {
        return status;
    }

    public void setStatus(StatutDepense status) {
        this.status = status;
    }

    public String getSrcDepense() {
        return srcDepense;
    }

    public void setSrcDepense(String srcDepense) {
        this.srcDepense = srcDepense;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
    }
}
