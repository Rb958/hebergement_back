package com.lsd.logement.entity.personnel;

import com.lsd.logement.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Employee implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Employee() {}

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public int getSalaireMois() {
        return salaireMois;
    }

    public void setSalaireMois(int salaireMois) {
        this.salaireMois = salaireMois;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getStMarital() {
        return stMarital;
    }

    public void setStMarital(String stMarital) {
        this.stMarital = stMarital;
    }

    public String getPersContact() {
        return persContact;
    }

    public void setPersContact(String persContact) {
        this.persContact = persContact;
    }

    public String getPersTelephone() {
        return persTelephone;
    }

    public void setPersTelephone(String persTelephone) {
        this.persTelephone = persTelephone;
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
}


