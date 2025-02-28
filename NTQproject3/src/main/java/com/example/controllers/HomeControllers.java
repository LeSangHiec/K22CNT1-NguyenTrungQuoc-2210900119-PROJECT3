package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllers {

    // Đường dẫn vào trang admin
    @GetMapping("/admin")
    public String admin() {
        return "admin/index";  // Trang admin tại templates/admin/index.html
    }

    // Đường dẫn vào trang người dùng
    @GetMapping("/")
    public String user() {
        return "index";  // Trang người dùng tại templates/user/index.html
    }
}
