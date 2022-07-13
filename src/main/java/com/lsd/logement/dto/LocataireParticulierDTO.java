package com.lsd.logement.dto;

import com.lsd.logement.entity.reservation.Booking;

import java.time.ZonedDateTime;
import java.util.List;

public class LocataireParticulierDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String type;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    private String nom;
    private String prenom;
    private String telephone;
    private String lieuTravail;
    private String cni;
    private String pjCni;
    private String stMaritale;
    private String persContact;
    private String persTelephone;
    private String pjContrat;
    private List<Booking> bookings;

    public LocataireParticulierDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
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

    public void setLieuTravail(String lieuTravail) {
        this.lieuTravail = lieuTravail;
    }

    public String getLieuTravail() {
        return this.lieuTravail;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getCni() {
        return this.cni;
    }

    public void setPjCni(String pjCni) {
        this.pjCni = pjCni;
    }

    public String getPjCni() {
        return this.pjCni;
    }

    public void setStMaritale(String stMaritale) {
        this.stMaritale = stMaritale;
    }

    public String getStMaritale() {
        return this.stMaritale;
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

    public void setPjContrat(String pjContrat) {
        this.pjContrat = pjContrat;
    }

    public String getPjContrat() {
        return this.pjContrat;
    }

    public void setBookings(java.util.List<Booking> bookings) {
        this.bookings = bookings;
    }

    public java.util.List<Booking> getBookings() {
        return this.bookings;
    }
}