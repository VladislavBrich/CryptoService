package com.example.CryptoService.services;

import com.example.CryptoService.models.Crypt;
import com.example.CryptoService.models.Notify;
import com.example.CryptoService.models.User;
import com.example.CryptoService.repositories.CryptRepository;
import com.example.CryptoService.repositories.NotifyRepository;
import com.example.CryptoService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyUserService {

    @Autowired
    private NotifyRepository notifyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CryptRepository cryptRepository;

    public void addNotify(String username, String cryptoName) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            userRepository.save(user);
        }
        Crypt crypt = cryptRepository.findByCryptoName(cryptoName);
        if (crypt == null) {
            throw new Exception("There is no such currency");
        }

        Notify notify = new Notify();
        notify.setUserNotify(user);
        notify.setCrypt(crypt);

        notify.setPrice(crypt.getPrice());
        //change to 1%
        Double change = crypt.getPrice() * 0.01d;

        notify.setMaxPrice(crypt.getPrice() + change);
        notify.setMinPrice(crypt.getPrice() - change);
        notifyRepository.save(notify);
    }

}
