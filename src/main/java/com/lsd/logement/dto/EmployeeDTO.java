package com.lsd.logement.dto;

import java.time.ZonedDateTime;

public class EmployeeDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String nom;
    private String prenom;
    private String telephone;
    private String fonction;
    private int salaireMois;
    private String cni;
    private String stMarital;
    private String persContact;
    private String persTelephone;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public EmployeeDTO() {
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

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getFonction() {
        return this.fonction;
    }

    public void setSalaireMois(int salaireMois) {
        this.salaireMois = salaireMois;
    }

    public int getSalaireMois() {
        return this.salaireMois;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getCni() {
        return this.cni;
    }

    public void setStMarital(String stMarital) {
        this.stMarital = stMarital;
    }

    public String getStMarital() {
        return this.stMarital;
    }

    public void setPersContact(String persContact) {
        this.persContact = persContact;
    }

    public String getPersContact() {
        return this.persContact;
    }

    public void setPersTelephone(String persTelephone) {
        this.persTelephone = persTelephone;
    }

    public String getPersTelephone() {
        return this.persTelephone;
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