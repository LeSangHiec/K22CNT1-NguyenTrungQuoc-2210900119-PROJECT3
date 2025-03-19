package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DatabaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/check-db")
    public String checkDatabaseConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "✅ Kết nối MySQL thành công!";
        } catch (Exception e) {
            return "❌ Lỗi kết nối MySQL: " + e.getMessage();
        }
    }
}
