package com.lsd.logement.entity.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lsd.logement.entity.AbstractEntity;
import com.lsd.logement.entity.client.LocataireParticulier;
import com.lsd.logement.entity.client.LocataireSociete;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.PaymentStatus;
import com.lsd.logement.entity.infra.Local;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Bail implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date dateEntre;
    @Temporal(TemporalType.DATE)
    private Date validite;
    private String numReservation;
    @Enumerated(EnumType.STRING)
    private BookingState statut;
    private int sejour;
    private int echeance;
    @Enumerated(EnumType.STRING)
    private PeriodUnit preriodUnit;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    @ManyToOne
    @JsonBackReference("local_bail")
    private Local local;
    @ManyToOne
    @JsonBackReference("bail_locataire_societe")
    private LocataireSociete locataireSociete;
    @ManyToOne
    @JsonBackReference("bail_locataire_particulier")
    private LocataireParticulier locataireParticulier;
    @OneToMany(mappedBy = "bail", cascade = CascadeType.ALL)
    @JsonManagedReference("bail_payment")
    private List<Payement> payements;

    public Bail() { }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateEntre() {
        return dateEntre;
    }

    public void setDateEntre(Date dateEntre) {
        this.dateEntre = dateEntre;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public int getEcheance() {
        return echeance;
    }

    public void setEcheance(int echeance) {
        this.echeance = echeance;
    }
}
