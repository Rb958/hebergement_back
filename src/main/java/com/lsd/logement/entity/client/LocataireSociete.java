package com.lsd.logement.entity.client;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lsd.logement.entity.AbstractEntity;
import com.lsd.logement.entity.reservation.Booking;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class LocataireSociete implements AbstractEntity<Integer>, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "locataireSociete")
    @JsonManagedReference("booking_locataire_societe")
    private List<Booking> bookings;

    public LocataireSociete() {
        bookings = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getNiu() {
        return niu;
    }

    public void setNiu(String niu) {
        this.niu = niu;
    }

    public String getNomGerant() {
        return nomGerant;
    }

    public void setNomGerant(String nomGerant) {
        this.nomGerant = nomGerant;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPersContact1() {
        return persContact1;
    }

    public void setPersContact1(String persContact1) {
        this.persContact1 = persContact1;
    }

    public String getPersTelephone1() {
        return persTelephone1;
    }

    public void setPersTelephone1(String persTelephone1) {
        this.persTelephone1 = persTelephone1;
    }

    public String getPersEmail1() {
        return persEmail1;
    }

    public void setPersEmail1(String persEmail1) {
        this.persEmail1 = persEmail1;
    }

    public String getPersContacter2() {
        return persContacter2;
    }

    public void setPersContacter2(String persContacter2) {
        this.persContacter2 = persContacter2;
    }

    public String getPersTelephone2() {
        return persTelephone2;
    }

    public void setPersTelephone2(String persTelephone2) {
        this.persTelephone2 = persTelephone2;
    }

    public String getPersEmail2() {
        return persEmail2;
    }

    public void setPersEmail2(String persEmail2) {
        this.persEmail2 = persEmail2;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocataireSociete that = (LocataireSociete) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(lastUpdatedAt, that.lastUpdatedAt) && Objects.equals(raisonSociale, that.raisonSociale) && Objects.equals(niu, that.niu) && Objects.equals(nomGerant, that.nomGerant) && Objects.equals(profession, that.profession) && Objects.equals(persContact1, that.persContact1) && Objects.equals(persTelephone1, that.persTelephone1) && Objects.equals(persEmail1, that.persEmail1) && Objects.equals(persContacter2, that.persContacter2) && Objects.equals(persTelephone2, that.persTelephone2) && Objects.equals(persEmail2, that.persEmail2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, lastUpdatedAt, raisonSociale, niu, nomGerant, profession, persContact1, persTelephone1, persEmail1, persContacter2, persTelephone2, persEmail2);
    }

    @Override
    public String toString() {
        return "LocataireSociete{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", niu='" + niu + '\'' +
                ", nomGerant='" + nomGerant + '\'' +
                ", profession='" + profession + '\'' +
                ", persContact1='" + persContact1 + '\'' +
                ", persTelephone1='" + persTelephone1 + '\'' +
                ", persEmail1='" + persEmail1 + '\'' +
                ", persContacter2='" + persContacter2 + '\'' +
                ", persTelephone2='" + persTelephone2 + '\'' +
                ", persEmail2='" + persEmail2 + '\'' +
                '}';
    }
}
