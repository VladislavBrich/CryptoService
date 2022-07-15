package com.example.CryptoService.controllers;

import com.example.CryptoService.models.Crypt;
import com.example.CryptoService.repositories.CryptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coin")
public class CryptRestController {

    @Autowired
    private CryptRepository cryptRepository;

    @GetMapping
    public List<Crypt> allCoin() {
        return cryptRepository.findAll();
    }

    @GetMapping("{cryptoName}")
    public Double price(@PathVariable String cryptoName) {
        return cryptRepository.findByCryptoName(cryptoName).getPrice();
    }

}
