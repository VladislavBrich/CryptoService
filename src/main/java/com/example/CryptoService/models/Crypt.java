package com.example.CryptoService.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "crypto_table")
@Data
public class Crypt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer cryptId;

    @Column
    private String cryptoName;

    @Column
    private Double price;

}
