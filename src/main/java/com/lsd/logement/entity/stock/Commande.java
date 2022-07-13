package com.lsd.logement.entity.stock;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lsd.logement.entity.AbstractEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Commande implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String refCmd;
    @ManyToOne
    @JsonBackReference("cmd_fournisseur")
    private Fournisseur fournisseur;
    private String demandeur;
    @Enumerated(EnumType.STRING)
    private StatutCommande statut;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonManagedReference("cmd_lignes_commande")
    private List<LigneCommande> commandes;

    public Commande() {}

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefCmd() {
        return refCmd;
    }

    public void setRefCmd(String refCmd) {
        this.refCmd = refCmd;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
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

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public List<LigneCommande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<LigneCommande> commandes) {
        this.commandes = commandes;
    }
}
