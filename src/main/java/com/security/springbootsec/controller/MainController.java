package com.security.springbootsec.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/time")
    public long getTime(){
        SecurityContext context = SecurityContextHolder.getContext();

        System.out.println("Login user : "+context.getAuthentication().getPrincipal());
        return System.currentTimeMillis();

    }
    @GetMapping("/admin/hello")
    public String hello(String name){
        return "Hello "+name;

    }



}
