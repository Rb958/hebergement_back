package com.lsd.logement.dto;

import java.time.ZonedDateTime;

public class FournisseurParticulierDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String nom;
    private String prenom;
    private String localisation;
    private String telephone;
    private String caCumule;
    private String echeance;
    private String mail1;
    private String mail2;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public FournisseurParticulierDTO() {
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

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLocalisation() {
        return this.localisation;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setCaCumule(String caCumule) {
        this.caCumule = caCumule;
    }

    public String getCaCumule() {
        return this.caCumule;
    }

    public void setEcheance(String echeance) {
        this.echeance = echeance;
    }

    public String getEcheance() {
        return this.echeance;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getMail1() {
        return this.mail1;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    public String getMail2() {
        return this.mail2;
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