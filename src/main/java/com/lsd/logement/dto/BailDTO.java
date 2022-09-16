package com.lsd.logement.dto;

import com.lsd.logement.entity.client.LocataireParticulier;
import com.lsd.logement.entity.client.LocataireSociete;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.PaymentStatus;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.reservation.BookingState;
import com.lsd.logement.entity.reservation.PeriodUnit;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class BailDTO extends AbstractDTO<Integer> {
    private Integer id;
    private Date dateEntre;
    private Date validite;
    private String numReservation;
    private BookingState statut;
    private int sejour;
    private int echeance;
    private PeriodUnit preriodUnit;
    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    private Local local;
    private LocataireSociete locataireSociete;
    private LocataireParticulier locataireParticulier;
    private List<Payement> payements;

    public BailDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setDateEntre(java.util.Date dateEntre) {
        this.dateEntre = dateEntre;
    }

    public java.util.Date getDateEntre() {
        return this.dateEntre;
    }

    public void setValidite(java.util.Date validite) {
        this.validite = validite;
    }

    public java.util.Date getValidite() {
        return this.validite;
    }

    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }

    public String getNumReservation() {
        return this.numReservation;
    }

    public void setStatut(BookingState statut) {
        this.statut = statut;
    }

    public BookingState getStatut() {
        return this.statut;
    }

    public void setSejour(int sejour) {
        this.sejour = sejour;
    }

    public int getSejour() {
        return this.sejour;
    }

    public void setPreriodUnit(PeriodUnit preriodUnit) {
        this.preriodUnit = preriodUnit;
    }

    public PeriodUnit getPreriodUnit() {
        return this.preriodUnit;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
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

    public void setLocal(Local local) {
        this.local = local;
    }

    public Local getLocal() {
        return this.local;
    }

    public void setLocataireSociete(LocataireSociete locataireSociete) {
        this.locataireSociete = locataireSociete;
    }

    public LocataireSociete getLocataireSociete() {
        return this.locataireSociete;
    }

    public void setLocataireParticulier(LocataireParticulier locataireParticulier) {
        this.locataireParticulier = locataireParticulier;
    }

    public LocataireParticulier getLocataireParticulier() {
        return this.locataireParticulier;
    }

    public void setPayements(java.util.List<com.lsd.logement.entity.finance.Payement> payements) {
        this.payements = payements;
    }

    public java.util.List<com.lsd.logement.entity.finance.Payement> getPayements() {
        return this.payements;
    }

    public int getEcheance() {
        return echeance;
    }

    public void setEcheance(int echeance) {
        this.echeance = echeance;
    }
}