package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllers {

    @GetMapping("/")
    public String home() {
        return "fe/humanuser"; // Trả về view index.html nằm trong templates/user/
    }
    
}
