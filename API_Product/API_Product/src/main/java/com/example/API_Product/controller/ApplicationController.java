package com.example.API_Product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("/hi")
    public String sayHi() {
        return "Hi there!";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello!";
    }
}
