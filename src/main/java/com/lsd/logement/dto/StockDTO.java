package com.lsd.logement.dto;

import com.lsd.logement.entity.stock.Article;

import java.time.ZonedDateTime;

public class StockDTO extends AbstractDTO<Integer> {
    private Integer id;
    private Article article;
    private int qte;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public StockDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getQte() {
        return this.qte;
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
}