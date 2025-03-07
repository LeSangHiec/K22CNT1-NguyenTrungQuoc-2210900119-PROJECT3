package com.example.controllers.admin;

import com.example.models.NTQDeThi;
import com.example.models.NTQDeThiCauHoi;
import com.example.services.NTQDeThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class NTQDeThiController {

    @Autowired
    private NTQDeThiService deThiService;

    // Hiển thị danh sách các đề thi
    @GetMapping("/de-thi")
    public String listDeThi(Model model) {
        List<NTQDeThi> deThiList = deThiService.getAllDeThi();
        model.addAttribute("deThiList", deThiList);
        return "admin/de-thi/index"; // View danh sách các đề thi
    }

    // Hiển thị form thêm mới đề thi
    @GetMapping("/de-thi/add")
    public String showAddForm(Model model) {
        model.addAttribute("deThi", new NTQDeThi());
        return "admin/de-thi/add";
    }

    // Lưu đề thi
    @PostMapping("/de-thi/save")
    public String saveDeThi(@ModelAttribute NTQDeThi deThi) {
        deThiService.saveDeThi(deThi);
        return "redirect:/admin/de-thi";
    }

    // Hiển thị form chỉnh sửa đề thi
    @GetMapping("/de-thi/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NTQDeThi deThi = deThiService.getDeThiById(id);
        model.addAttribute("deThi", deThi);
        return "admin/de-thi/edit";
    }

    // Xóa đề thi
    @GetMapping("/de-thi/delete/{id}")
    public String deleteDeThi(@PathVariable Long id) {
        deThiService.deleteDeThi(id);
        return "redirect:/admin/de-thi";
    }
}
