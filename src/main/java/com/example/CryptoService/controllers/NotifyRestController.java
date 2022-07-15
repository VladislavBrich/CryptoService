package com.example.CryptoService.controllers;

import com.example.CryptoService.services.NotifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotifyRestController {

    @Autowired
    private NotifyUserService userService;

    @PostMapping
    public ResponseEntity<?> addNotify(@RequestParam(name = "username") String userName,
                                       @RequestParam(name = "cryptName") String cryptName) {
        try {
            userService.addNotify(userName, cryptName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
