package com.lsd.logement.dto;

import com.lsd.logement.entity.finance.StatutDepense;
import com.lsd.logement.entity.infra.Local;

import java.time.ZonedDateTime;

public class DepenseDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String demandeur;
    private Local local;
    private String categorie;
    private String type;
    private StatutDepense status;
    private String srcDepense;
    private int montant;
    private String validatedBy;
    private String commentaire;
    private String pieceJointe;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

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
        this.lastUpdatedAt = LastUpdatedAt;
    }

    public java.time.ZonedDateTime getLastUpdatedAt() {
        return this.lastUpdatedAt;
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