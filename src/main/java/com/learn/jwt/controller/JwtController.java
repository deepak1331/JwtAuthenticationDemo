package com.learn.jwt.controller;

import com.learn.jwt.model.JwtRequest;
import com.learn.jwt.model.JwtResponse;
import com.learn.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        JwtResponse authenticate = jwtService.authenticate(jwtRequest);
        return ResponseEntity.ok(authenticate);
    }
}
