package com.example.CryptoService.services;

import com.example.CryptoService.models.Crypt;
import com.example.CryptoService.repositories.CryptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BaseConfig {

    @Value("${crypt.id}")
    private String ids;

    @Value("${crypt.symbol}")
    private String symbols;

    @Autowired
    private CryptRepository cryptRepository;

    @PostConstruct
    public void postInit() {
        Integer total = cryptRepository.countAllBy();
        if (total != 0) {
            return;
        }
        String[] id = ids.split(",");
        String[] symbol = symbols.split(",");
        for (int i = 0; i < id.length; i++) {
            Crypt c = new Crypt();
            c.setCryptId(Integer.parseInt(id[i]));
            c.setCryptoName(symbol[i]);
            cryptRepository.save(c);
        }
    }

}
