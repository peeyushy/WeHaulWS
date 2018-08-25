package com.ermarketplace.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"", "/", "ERMarketPlace"})
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome ERMarketPlace.";
    }
}
