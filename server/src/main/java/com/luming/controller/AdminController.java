package com.luming.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/admin")
public class AdminController {
    
    
    @RequestMapping("main")
    public String toMain() {
        return "http://localhost:3000/helloWorld";
    }
    
}
