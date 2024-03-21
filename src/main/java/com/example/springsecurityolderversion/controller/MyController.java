package com.example.springsecurityolderversion.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/customlogin")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/all")
    public String allPage(){
        return "all";
    }
}
