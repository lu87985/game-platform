package com.luming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    
    @RequestMapping("main")
    public String toMain() {
        return "hello world";
    }
    
}
