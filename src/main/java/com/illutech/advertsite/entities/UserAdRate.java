package com.illutech.advertsite.entities;

import jakarta.persistence.*;

@Entity
public class UserAdRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    private Users user;
    @ManyToOne
    private Advert advert;

}
