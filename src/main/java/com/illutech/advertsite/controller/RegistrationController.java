package com.illutech.advertsite.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @GetMapping("/")
    public String hello(){return "hello world";}
}
