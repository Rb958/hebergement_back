package com.lsd.logement.dto;

import com.lsd.logement.entity.client.LocataireParticulier;
import com.lsd.logement.entity.client.LocataireSociete;
import com.lsd.logement.entity.infra.Local;
import com.lsd.logement.entity.reservation.BookingState;
import com.lsd.logement.entity.finance.Payement;
import com.lsd.logement.entity.finance.PaymentStatus;
import com.lsd.logement.entity.reservation.PeriodUnit;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class BookingDTO extends AbstractDTO<Integer> {
    private Integer id;
    private Date dateReservation;
    private String nom;
    private String prenom;
    private String telephone;
    private Date validite;
    private String numReservation;
    private int sejour;
    private int paidAmount;
    private int totalAmount;
    private int restAmount;
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

    public void setDateReservation(java.util.Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public java.util.Date getDateReservation() {
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

    public int getRestAmount() {
        return restAmount;
    }

    public void setRestAmount(int restAmount) {
        this.restAmount = restAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }
}