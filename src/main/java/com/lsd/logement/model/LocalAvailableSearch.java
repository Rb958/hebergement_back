package com.lsd.logement.model;

import java.time.ZonedDateTime;
import java.util.Date;

public class LocalAvailableSearch {
    private String typeLocal;
    private Date startDate;
    private Date endDate;

    public LocalAvailableSearch() {
    }

    public String getTypeLocal() {
        return typeLocal;
    }

    public void setTypeLocal(String typeLocal) {
        this.typeLocal = typeLocal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
