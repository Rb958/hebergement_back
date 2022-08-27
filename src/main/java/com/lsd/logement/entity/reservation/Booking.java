package com.lsd.logement.entity.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lsd.logement.entity.*;
import com.lsd.logement.entity.client.LocataireParticulier;
import com.lsd.logement.entity.client.LocataireSociete;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.PaymentStatus;
import com.lsd.logement.entity.infra.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Booking implements AbstractEntity<Integer>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date dateReservation;
    private String nom;
    private String prenom;
    private String telephone;
    @Temporal(TemporalType.DATE)
    private Date validite;
    private String numReservation;
    private int sejour;
    @Enumerated(EnumType.STRING)
    private BookingState statut;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private PeriodUnit preriodUnit;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    @ManyToOne
    @JsonBackReference("booking_local")
    private Local local;
    @ManyToOne
    @JsonBackReference("booking_locataire_societe")
    private LocataireSociete locataireSociete;
    @ManyToOne
    @JsonBackReference("booking_locataire_particulier")
    private LocataireParticulier locataireParticulier;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference("payment_booking")
    private List<Payement> payements;

    public Booking() {
        payements = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
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

    public Date getValidite() {
        return validite;
    }

    public void setValidite(Date validite) {
        this.validite = validite;
    }

    public String getNumReservation() {
        return numReservation;
    }

    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }

    public BookingState getStatut() {
        return statut;
    }

    public void setStatut(BookingState statut) {
        this.statut = statut;
    }

    public int getSejour() {
        return sejour;
    }

    public void setSejour(int sejour) {
        this.sejour = sejour;
    }

    public PeriodUnit getPreriodUnit() {
        return preriodUnit;
    }

    public void setPreriodUnit(PeriodUnit preriodUnit) {
        this.preriodUnit = preriodUnit;
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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public LocataireSociete getLocataireSociete() {
        return locataireSociete;
    }

    public void setLocataireSociete(LocataireSociete locataireSociete) {
        this.locataireSociete = locataireSociete;
    }

    public LocataireParticulier getLocataireParticulier() {
        return locataireParticulier;
    }

    public void setLocataireParticulier(LocataireParticulier locataireParticulier) {
        this.locataireParticulier = locataireParticulier;
    }

    public List<Payement> getPayements() {
        return payements;
    }

    public void setPayements(List<Payement> payements) {
        this.payements = payements;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateReservation=" + dateReservation +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", validite=" + validite +
                ", numReservation='" + numReservation + '\'' +
                ", statut=" + statut +
                ", sejour=" + sejour +
                ", preriodUnit=" + preriodUnit +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", local=" + local +
                ", locataireSociete=" + locataireSociete +
                ", locataireParticulier=" + locataireParticulier +
                ", payements=" + payements +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
