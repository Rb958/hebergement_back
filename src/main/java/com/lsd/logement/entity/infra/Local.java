package com.lsd.logement.entity.infra;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lsd.logement.entity.converter.ImmobilisationConverter;
import com.lsd.logement.entity.AbstractEntity;
import com.lsd.logement.entity.reservation.Booking;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Local implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ville;
    private String pays;
    private String quartier;
    private String immeuble;
    @Enumerated(EnumType.STRING)
    private TypeLocal typeLocal;
    private String nomLocal;
    private String numeroLocal;
    @Enumerated(EnumType.STRING)
    private EtageEnum etage;
    private String localisation;
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private LocateState status;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    @OneToMany(mappedBy = "local")
    @JsonManagedReference("booking_local")
    private List<Booking> bookings;
    @Convert(converter = ImmobilisationConverter.class)
    @Column(columnDefinition = "json")
    private List<Immobilisation> immobilisations;

    public Local() {
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(String immeuble) {
        this.immeuble = immeuble;
    }

    public TypeLocal getTypeLocal() {
        return typeLocal;
    }

    public void setTypeLocal(TypeLocal typeLocal) {
        this.typeLocal = typeLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public String getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(String numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public EtageEnum getEtage() {
        return etage;
    }

    public void setEtage(EtageEnum etage) {
        this.etage = etage;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public CategorieEnum getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieEnum categorie) {
        this.categorie = categorie;
    }

    public boolean isFumeur() {
        return fumeur;
    }

    public void setFumeur(boolean fumeur) {
        this.fumeur = fumeur;
    }

    public boolean isClim() {
        return clim;
    }

    public void setClim(boolean clim) {
        this.clim = clim;
    }

    public boolean isPiscine() {
        return piscine;
    }

    public void setPiscine(boolean piscine) {
        this.piscine = piscine;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isAnimaux() {
        return animaux;
    }

    public void setAnimaux(boolean animaux) {
        this.animaux = animaux;
    }

    public boolean isChauffage() {
        return chauffage;
    }

    public void setChauffage(boolean chauffage) {
        this.chauffage = chauffage;
    }

    public boolean isParkingIn() {
        return parkingIn;
    }

    public void setParkingIn(boolean parkingIn) {
        this.parkingIn = parkingIn;
    }

    public boolean isGadienJour() {
        return gadienJour;
    }

    public void setGadienJour(boolean gadienJour) {
        this.gadienJour = gadienJour;
    }

    public boolean isGardientNuit() {
        return gardientNuit;
    }

    public void setGardientNuit(boolean gardientNuit) {
        this.gardientNuit = gardientNuit;
    }

    public boolean isGroupeElect() {
        return groupeElect;
    }

    public void setGroupeElect(boolean groupeElect) {
        this.groupeElect = groupeElect;
    }

    public int getNbrChambre() {
        return nbrChambre;
    }

    public void setNbrChambre(int nbrChambre) {
        this.nbrChambre = nbrChambre;
    }

    public int getNbrDouche() {
        return nbrDouche;
    }

    public void setNbrDouche(int nbrDouche) {
        this.nbrDouche = nbrDouche;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTypePrix() {
        return typePrix;
    }

    public void setTypePrix(String typePrix) {
        this.typePrix = typePrix;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public LocateState getStatus() {
        return status;
    }

    public void setStatus(LocateState status) {
        this.status = status;
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

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public void increaseCaWith(int amount){
        this.ca += amount;
    }

    public void decreaseCaWith(int amount){
        this.ca -= amount;
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
        Local local = (Local) o;
        return fumeur == local.fumeur && clim == local.clim && piscine == local.piscine && wifi == local.wifi && animaux == local.animaux && chauffage == local.chauffage && parkingIn == local.parkingIn && gadienJour == local.gadienJour && gardientNuit == local.gardientNuit && groupeElect == local.groupeElect && nbrChambre == local.nbrChambre && nbrDouche == local.nbrDouche && ca == local.ca && prix == local.prix && status == local.status && Objects.equals(id, local.id) && Objects.equals(ville, local.ville) && Objects.equals(pays, local.pays) && Objects.equals(quartier, local.quartier) && Objects.equals(immeuble, local.immeuble) && typeLocal == local.typeLocal && Objects.equals(nomLocal, local.nomLocal) && Objects.equals(numeroLocal, local.numeroLocal) && etage == local.etage && Objects.equals(localisation, local.localisation) && categorie == local.categorie && Objects.equals(typePrix, local.typePrix) && Objects.equals(contrat, local.contrat) && Objects.equals(createdAt, local.createdAt) && Objects.equals(lastUpdatedAt, local.lastUpdatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ville, pays, quartier, immeuble, typeLocal, nomLocal, numeroLocal, etage, localisation, categorie, fumeur, clim, piscine, wifi, animaux, chauffage, parkingIn, gadienJour, gardientNuit, groupeElect, nbrChambre, nbrDouche, ca, prix, typePrix, contrat, status, createdAt, lastUpdatedAt);
    }

    public List<Immobilisation> getImmobilisations() {
        return immobilisations;
    }

    public void setImmobilisations(List<Immobilisation> immobilisations) {
        this.immobilisations = immobilisations;
    }
}
