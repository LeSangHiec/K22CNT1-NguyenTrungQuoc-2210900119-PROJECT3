package com.example.controllers;

import com.example.models.NTQNguoiDung;
import com.example.repository.NTQNguoiDungRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserAuthController {

    @Autowired
    private NTQNguoiDungRepository nguoiDungRepository;

    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new NTQNguoiDung());
        return "fe/register";  // file templates/fe/register.html
    }

    // Xử lý đăng ký
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") NTQNguoiDung user) {
        // (Có thể thêm kiểm tra trùng lặp username/email)
        user.setTrangThai("HOAT_DONG");
        // Giả sử người dùng tự chọn vai trò (chỉ cho phép HOC_SINH và GIAO_VIEN)
        if (user.getVaiTro() == null || user.getVaiTro().trim().isEmpty()) {
            user.setVaiTro("HOC_SINH");
        }
        nguoiDungRepository.save(user);
        return "redirect:/login?registered";
    }

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm() {
        return "fe/login"; // file templates/fe/login.html
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        Optional<NTQNguoiDung> userOpt = nguoiDungRepository.findByTenDangNhap(username);
        if (userOpt.isPresent() && userOpt.get().getMatKhau().equals(password)) {
            // Lưu thông tin user vào session
            session.setAttribute("loggedInUser", userOpt.get());
            return "redirect:/"; // chuyển hướng về trang chủ hoặc trang cá nhân
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            return "fe/login";
        }
    }

    // Xử lý đăng xuất
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    // Hiển thị trang Profile
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        NTQNguoiDung user = (NTQNguoiDung) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "fe/profile";  // File templates/fe/profile.html
    }

    // Hiển thị form chỉnh sửa Profile (không có avatar)
    @GetMapping("/profile/edit")
    public String editProfile(HttpSession session, Model model) {
        NTQNguoiDung user = (NTQNguoiDung) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "fe/profile_edit";  // File templates/fe/profile_edit.html
    }

    // Xử lý cập nhật Profile (không xử lý avatar)
    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute("user") NTQNguoiDung formUser,
                                HttpSession session) {
        NTQNguoiDung currentUser = (NTQNguoiDung) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        // Cập nhật các thông tin có thể chỉnh sửa
        currentUser.setEmail(formUser.getEmail());
        // Bạn có thể cập nhật thêm các trường khác nếu cần (không xử lý avatar)
        nguoiDungRepository.save(currentUser);
        // Cập nhật lại thông tin trong session
        session.setAttribute("loggedInUser", currentUser);
        return "redirect:/profile";
    }
}
