package com.lsd.logement.dto;

import com.lsd.logement.entity.stock.Fournisseur;
import com.lsd.logement.entity.stock.LigneCommande;
import com.lsd.logement.entity.stock.StatutCommande;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class CommandeDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String refCmd;
    private Fournisseur fournisseur;
    private String demandeur;
    private StatutCommande statut;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    private Date dateLivraison;
    private List<LigneCommande> commandes;

    public CommandeDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setRefCmd(String refCmd) {
        this.refCmd = refCmd;
    }

    public String getRefCmd() {
        return this.refCmd;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Fournisseur getFournisseur() {
        return this.fournisseur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getDemandeur() {
        return this.demandeur;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    public StatutCommande getStatut() {
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

    public void setDateLivraison(java.util.Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public java.util.Date getDateLivraison() {
        return this.dateLivraison;
    }

    public void setCommandes(java.util.List<LigneCommande> commandes) {
        this.commandes = commandes;
    }

    public java.util.List<LigneCommande> getCommandes() {
        return this.commandes;
    }
}