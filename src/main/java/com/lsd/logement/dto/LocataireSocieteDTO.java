package com.lsd.logement.dto;

import com.lsd.logement.entity.reservation.Booking;

import java.time.ZonedDateTime;
import java.util.List;

public class LocataireSocieteDTO extends AbstractDTO<Integer> {
    private Integer id;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    private String raisonSociale;
    private String niu;
    private String nomGerant;
    private String profession;
    private String persContact1;
    private String persTelephone1;
    private String persEmail1;
    private String persContacter2;
    private String persTelephone2;
    private String persEmail2;
    private List<Booking> bookings;

    public LocataireSocieteDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
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

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    public void setNiu(String niu) {
        this.niu = niu;
    }

    public String getNiu() {
        return this.niu;
    }

    public void setNomGerant(String nomGerant) {
        this.nomGerant = nomGerant;
    }

    public String getNomGerant() {
        return this.nomGerant;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setPersContact1(String persContact1) {
        this.persContact1 = persContact1;
    }

    public String getPersContact1() {
        return this.persContact1;
    }

    public void setPersTelephone1(String persTelephone1) {
        this.persTelephone1 = persTelephone1;
    }

    public String getPersTelephone1() {
        return this.persTelephone1;
    }

    public void setPersEmail1(String persEmail1) {
        this.persEmail1 = persEmail1;
    }

    public String getPersEmail1() {
        return this.persEmail1;
    }

    public void setPersContacter2(String persContacter2) {
        this.persContacter2 = persContacter2;
    }

    public String getPersContacter2() {
        return this.persContacter2;
    }

    public void setPersTelephone2(String persTelephone2) {
        this.persTelephone2 = persTelephone2;
    }

    public String getPersTelephone2() {
        return this.persTelephone2;
    }

    public void setPersEmail2(String persEmail2) {
        this.persEmail2 = persEmail2;
    }

    public String getPersEmail2() {
        return this.persEmail2;
    }

    public void setBookings(java.util.List<Booking> bookings) {
        this.bookings = bookings;
    }

    public java.util.List<Booking> getBookings() {
        return this.bookings;
    }
}