package com.example.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/index"; // File dashboard.html nằm trong templates/admin/
    }
}
