package com.example.CryptoService.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "_notify")
@Data
public class Notify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userNotify;

    @ManyToOne
    private Crypt crypt;

    private Double price;

    // maximum price change
    private Double maxPrice;

    //minimum price change
    private Double minPrice;

}
