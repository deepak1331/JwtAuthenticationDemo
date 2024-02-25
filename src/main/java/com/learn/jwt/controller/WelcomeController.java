package com.learn.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome/{msg}")
    public ResponseEntity<String> welcome(@PathVariable String msg){
        return ResponseEntity.ok("Welcome, " + msg.toUpperCase()+ " !\nThis is not allowed for UnAuthenticated users");
    }

    @GetMapping("/welcome/users")
    public ResponseEntity<String> getUser(){
        String data = "{\n" +
                "\t\"username\":\"Deepak\",\n" +"}";
        return ResponseEntity.ok(data);
    }
}
