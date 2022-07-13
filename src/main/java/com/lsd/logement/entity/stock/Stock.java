package com.lsd.logement.entity.stock;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lsd.logement.entity.AbstractEntity;
import com.lsd.logement.entity.stock.Article;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Stock implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JsonBackReference("stock_article")
    private Article article;
    private int qte;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastUpdatedAt;

    public Stock() {}

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
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
}
