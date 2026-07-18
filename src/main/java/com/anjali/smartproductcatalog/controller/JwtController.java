package com.anjali.smartproductcatalog.controller;

import com.anjali.smartproductcatalog.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.anjali.smartproductcatalog.dto.LoginRequest;
import com.anjali.smartproductcatalog.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping("/username")
    public String getUsername() {

        String token = jwtService.generateToken("Anjali");

        return jwtService.extractUsername(token);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

}