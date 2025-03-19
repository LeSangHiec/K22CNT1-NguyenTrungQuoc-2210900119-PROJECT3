package com.example.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminControllers {

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/index"; // Trả về file admin/index.html trong templates
    }
}
