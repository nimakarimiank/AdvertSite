package com.illutech.advertsite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Advert {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String advertText;
    private boolean validated;
    private boolean deleted;

    public Advert() {
    }

    public String getAdvertText() {
        return advertText;
    }

    public void setAdvertText(String advertText) {
        this.advertText = advertText;
    }

    public boolean isValidated() {
        return validated;
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Advert(String advertText, boolean validated, boolean deleted) {
        this.advertText = advertText;
        this.validated = validated;
        this.deleted = deleted;
    }
}
