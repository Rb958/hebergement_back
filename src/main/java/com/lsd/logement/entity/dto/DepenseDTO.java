package com.lsd.logement.entity.dto;

import com.lsd.logement.entity.infra.Local;

public class DepenseDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String demandeur;
    private Local local;
    private String categorie;
    private int montant;
    private String commentaire;
    private String pieceJointe;
    private ZonedDateTime createdAt;
    private ZonedDateTime LastUpdatedAt;

    public DepenseDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getDemandeur() {
        return this.demandeur;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Local getLocal() {
        return this.local;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getMontant() {
        return this.montant;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setPieceJointe(String pieceJointe) {
        this.pieceJointe = pieceJointe;
    }

    public String getPieceJointe() {
        return this.pieceJointe;
    }

    public void setCreatedAt(java.time.ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setLastUpdatedAt(java.time.ZonedDateTime LastUpdatedAt) {
        this.LastUpdatedAt = LastUpdatedAt;
    }

    public java.time.ZonedDateTime getLastUpdatedAt() {
        return this.LastUpdatedAt;
    }
}