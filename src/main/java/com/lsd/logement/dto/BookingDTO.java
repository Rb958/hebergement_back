package com.lsd.logement.dto;

import com.lsd.logement.entity.client.LocataireParticulier;
import com.lsd.logement.entity.client.LocataireSociete;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.reservation.BookingState;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.PaymentStatus;
import com.lsd.logement.entity.reservation.PeriodUnit;

import java.time.ZonedDateTime;
import java.util.List;

public class BookingDTO extends AbstractDTO<Integer> {
    private Integer id;
    private ZonedDateTime dateReservation;
    private String nom;
    private String prenom;
    private String telephone;
    private ZonedDateTime validite;
    private String numReservation;
    private int sejour;
    private BookingState statut;
    private PaymentStatus paymentStatus;
    private PeriodUnit preriodUnit;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    private Local local;
    private LocataireSociete locataireSociete;
    private LocataireParticulier locataireParticulier;
    private List<Payement> payements;

    public BookingDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setDateReservation(java.time.ZonedDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public java.time.ZonedDateTime getDateReservation() {
        return this.dateReservation;
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

    public void setValidite(java.time.ZonedDateTime validite) {
        this.validite = validite;
    }

    public java.time.ZonedDateTime getValidite() {
        return this.validite;
    }

    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }

    public String getNumReservation() {
        return this.numReservation;
    }

    public void setSejour(int sejour) {
        this.sejour = sejour;
    }

    public int getSejour() {
        return this.sejour;
    }

    public void setStatut(BookingState statut) {
        this.statut = statut;
    }

    public BookingState getStatut() {
        return this.statut;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPreriodUnit(PeriodUnit preriodUnit) {
        this.preriodUnit = preriodUnit;
    }

    public PeriodUnit getPreriodUnit() {
        return this.preriodUnit;
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

    public void setPayements(java.util.List<Payement> payements) {
        this.payements = payements;
    }

    public java.util.List<Payement> getPayements() {
        return this.payements;
    }
}