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
public class LocataireParticulier implements AbstractEntity<Integer>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(insertable = false, updatable = false)
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
    @OneToMany(mappedBy = "locataireParticulier")
    @JsonManagedReference("booking_locataire_particulier")
    private List<Booking> bookings;

    public LocataireParticulier() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLieuTravail() {
        return lieuTravail;
    }

    public void setLieuTravail(String lieuTravail) {
        this.lieuTravail = lieuTravail;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getPjCni() {
        return pjCni;
    }

    public void setPjCni(String pjCni) {
        this.pjCni = pjCni;
    }

    public String getStMaritale() {
        return stMaritale;
    }

    public void setStMaritale(String stMaritale) {
        this.stMaritale = stMaritale;
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

    public String getPjContrat() {
        return pjContrat;
    }

    public void setPjContrat(String pjContrat) {
        this.pjContrat = pjContrat;
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
        LocataireParticulier that = (LocataireParticulier) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(createdAt, that.createdAt) && Objects.equals(lastUpdatedAt, that.lastUpdatedAt) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(telephone, that.telephone) && Objects.equals(lieuTravail, that.lieuTravail) && Objects.equals(cni, that.cni) && Objects.equals(pjCni, that.pjCni) && Objects.equals(stMaritale, that.stMaritale) && Objects.equals(persContact, that.persContact) && Objects.equals(persTelephone, that.persTelephone) && Objects.equals(pjContrat, that.pjContrat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, createdAt, lastUpdatedAt, nom, prenom, telephone, lieuTravail, cni, pjCni, stMaritale, persContact, persTelephone, pjContrat);
    }

    @Override
    public String toString() {
        return "LocataireParticulier{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", lieuTravail='" + lieuTravail + '\'' +
                ", cni='" + cni + '\'' +
                ", pjCni='" + pjCni + '\'' +
                ", stMaritale='" + stMaritale + '\'' +
                ", persContact='" + persContact + '\'' +
                ", persTelephone='" + persTelephone + '\'' +
                ", pjContrat='" + pjContrat + '\'' +
                '}';
    }
}
