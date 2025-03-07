package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllers {

    @GetMapping("/")
    public String home() {
        return "index"; // Trả về trang index.html trong thư mục templates
    }
    
}