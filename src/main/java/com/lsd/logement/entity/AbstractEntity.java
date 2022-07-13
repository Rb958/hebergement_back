package com.lsd.logement.entity;

import java.time.ZonedDateTime;

public interface AbstractEntity<I> {
    I getId();
    void setId(I id);

    ZonedDateTime getCreatedAt();
    void setCreatedAt(ZonedDateTime date);

    ZonedDateTime getLastUpdatedAt();
    void setLastUpdatedAt(ZonedDateTime date);
}
