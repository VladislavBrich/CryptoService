package com.example.CryptoService.repositories;

import com.example.CryptoService.models.Crypt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptRepository extends JpaRepository<Crypt, Long> {

    Integer countAllBy();

    Crypt findByCryptoName(String cryptoName);

}
