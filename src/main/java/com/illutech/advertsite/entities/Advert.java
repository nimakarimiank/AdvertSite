package com.illutech.advertsite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Advert {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String advertText;
    private String price;
    private boolean validated;
    private boolean deleted;
    @OneToMany(mappedBy = "advert")
    private Collection<UserAdRate> adRate;


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

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public Advert(String advertText, boolean validated, boolean deleted) {
        this.advertText = advertText;
        this.validated = validated;
        this.deleted = deleted;
    }
    public Advert(String advertText,String price, boolean validated, boolean deleted) {
        this.price = price;
        this.advertText = advertText;
        this.validated = validated;
        this.deleted = deleted;
    }
}
