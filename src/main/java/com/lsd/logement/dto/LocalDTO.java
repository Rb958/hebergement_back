package com.lsd.logement.dto;

import com.lsd.logement.entity.infra.CategorieEnum;
import com.lsd.logement.entity.infra.EtageEnum;
import com.lsd.logement.entity.infra.LocateState;
import com.lsd.logement.entity.infra.TypeLocal;
import com.lsd.logement.entity.reservation.Booking;

import java.time.ZonedDateTime;
import java.util.List;

public class LocalDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String ville;
    private String pays;
    private String quartier;
    private String immeuble;
    private TypeLocal typeLocal;
    private String nomLocal;
    private String numeroLocal;
    private EtageEnum etage;
    private String localisation;
    private CategorieEnum categorie;
    private boolean fumeur;
    private boolean clim;
    private boolean piscine;
    private boolean wifi;
    private boolean animaux;
    private boolean chauffage;
    private boolean parkingIn;
    private boolean gadienJour;
    private boolean gardientNuit;
    private boolean groupeElect;
    private int nbrChambre;
    private int nbrDouche;
    private int ca;
    private int prix;
    private String typePrix;
    private String contrat;
    private LocateState status;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    private List<Booking> bookings;

    public LocalDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return this.ville;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPays() {
        return this.pays;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getQuartier() {
        return this.quartier;
    }

    public void setImmeuble(String immeuble) {
        this.immeuble = immeuble;
    }

    public String getImmeuble() {
        return this.immeuble;
    }

    public void setTypeLocal(TypeLocal typeLocal) {
        this.typeLocal = typeLocal;
    }

    public TypeLocal getTypeLocal() {
        return this.typeLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public String getNomLocal() {
        return this.nomLocal;
    }

    public void setNumeroLocal(String numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public String getNumeroLocal() {
        return this.numeroLocal;
    }

    public void setEtage(EtageEnum etage) {
        this.etage = etage;
    }

    public EtageEnum getEtage() {
        return this.etage;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLocalisation() {
        return this.localisation;
    }

    public void setCategorie(CategorieEnum categorie) {
        this.categorie = categorie;
    }

    public CategorieEnum getCategorie() {
        return this.categorie;
    }

    public void setFumeur(boolean fumeur) {
        this.fumeur = fumeur;
    }

    public boolean getFumeur() {
        return this.fumeur;
    }

    public void setClim(boolean clim) {
        this.clim = clim;
    }

    public boolean getClim() {
        return this.clim;
    }

    public void setPiscine(boolean piscine) {
        this.piscine = piscine;
    }

    public boolean getPiscine() {
        return this.piscine;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean getWifi() {
        return this.wifi;
    }

    public void setAnimaux(boolean animaux) {
        this.animaux = animaux;
    }

    public boolean getAnimaux() {
        return this.animaux;
    }

    public void setChauffage(boolean chauffage) {
        this.chauffage = chauffage;
    }

    public boolean getChauffage() {
        return this.chauffage;
    }

    public void setParkingIn(boolean parkingIn) {
        this.parkingIn = parkingIn;
    }

    public boolean getParkingIn() {
        return this.parkingIn;
    }

    public void setGadienJour(boolean gadienJour) {
        this.gadienJour = gadienJour;
    }

    public boolean getGadienJour() {
        return this.gadienJour;
    }

    public void setGardientNuit(boolean gardientNuit) {
        this.gardientNuit = gardientNuit;
    }

    public boolean getGardientNuit() {
        return this.gardientNuit;
    }

    public void setGroupeElect(boolean groupeElect) {
        this.groupeElect = groupeElect;
    }

    public boolean getGroupeElect() {
        return this.groupeElect;
    }

    public void setNbrChambre(int nbrChambre) {
        this.nbrChambre = nbrChambre;
    }

    public int getNbrChambre() {
        return this.nbrChambre;
    }

    public void setNbrDouche(int nbrDouche) {
        this.nbrDouche = nbrDouche;
    }

    public int getNbrDouche() {
        return this.nbrDouche;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public int getCa() {
        return this.ca;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPrix() {
        return this.prix;
    }

    public void setTypePrix(String typePrix) {
        this.typePrix = typePrix;
    }

    public String getTypePrix() {
        return this.typePrix;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getContrat() {
        return this.contrat;
    }

    public void setStatus(LocateState status) {
        this.status = status;
    }

    public LocateState getStatus() {
        return this.status;
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

    public void setBookings(java.util.List<Booking> bookings) {
        this.bookings = bookings;
    }

    public java.util.List<Booking> getBookings() {
        return this.bookings;
    }
}