package com.example.CryptoService.repositories;

import com.example.CryptoService.models.Crypt;
import com.example.CryptoService.models.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotifyRepository extends JpaRepository<Notify, Long> {

    @Query(value = "SELECT n FROM Notify n WHERE n.crypt=?1 AND n.maxPrice <= ?2")
    List<Notify> getNotifyListPP(Crypt crypt, Double price);

    @Query(value = "SELECT n FROM Notify n WHERE n.crypt=?1 AND n.minPrice >= ?2")
    List<Notify> getNotifyListMM(Crypt crypt, Double price);

}
