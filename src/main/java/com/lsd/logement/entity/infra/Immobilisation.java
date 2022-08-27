package com.lsd.logement.entity.infra;

import java.io.Serializable;

public class Immobilisation implements Serializable {
    private String designation;
    private int prix;
    private int qte;
    private String description;

    public Immobilisation() {}

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
