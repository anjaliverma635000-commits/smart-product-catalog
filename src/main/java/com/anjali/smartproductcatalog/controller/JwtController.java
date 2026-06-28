package com.anjali.smartproductcatalog.controller;

import com.anjali.smartproductcatalog.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/username")
    public String getUsername() {

        String token = jwtService.generateToken("Anjali");

        return jwtService.extractUsername(token);
    }
}