package com.example.controllers.admin;

import com.example.models.NTQNguoiDung;
import com.example.services.NTQNguoiDungService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class NTQNguoiDungController {

    @Autowired
    private NTQNguoiDungService nguoiDungService;

    // Hiển thị danh sách người dùng với phân trang
    @GetMapping("/user")
    public String listUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size,
                            Model model) {
        Page<NTQNguoiDung> nguoiDungPage = nguoiDungService.getAllUsersWithPagination(page, size);
        model.addAttribute("list", nguoiDungPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", nguoiDungPage.getTotalPages());
        return "admin/user/index";  // Trả về trang hiển thị danh sách người dùng
    }

    // Hiển thị form thêm người dùng
    @GetMapping("/user/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new NTQNguoiDung());
        return "admin/user/add";  // Trang thêm người dùng
    }

    // Lưu người dùng (thêm/sửa)
    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute NTQNguoiDung nguoiDung) {
        nguoiDungService.saveUser(nguoiDung);
        return "redirect:/admin/user";  // Quay lại danh sách người dùng
    }

    // Hiển thị form sửa người dùng
    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<NTQNguoiDung> nguoiDung = nguoiDungService.getUserById(id);
        if (nguoiDung.isPresent()) {
            model.addAttribute("user", nguoiDung.get());
            return "admin/user/edit";  // Trang sửa người dùng
        } else {
            return "redirect:/admin/user";
        }
    }

    // Xóa người dùng
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        nguoiDungService.deleteUser(id);
        return "redirect:/admin/user";  // Quay lại danh sách người dùng
    }
}
