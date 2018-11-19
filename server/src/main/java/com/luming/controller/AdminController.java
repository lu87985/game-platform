package com.luming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdminController {
    
    
    @RequestMapping("main")
    public String toMain() {
        return "hello world";
    }
    
}
