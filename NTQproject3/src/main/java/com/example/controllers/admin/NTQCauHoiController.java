package com.example.controllers.admin;

import com.example.models.NTQCauHoi;
import com.example.services.NTQCauHoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

@Controller
@RequestMapping("/admin")
public class NTQCauHoiController {

    @Autowired
    private NTQCauHoiService cauHoiService;

    // Hiển thị danh sách câu hỏi với phân trang
    @GetMapping("/questions")
    public String listQuestions(@RequestParam(value = "page", defaultValue = "0") int page, 
                                @RequestParam(value = "size", defaultValue = "5") int size, 
                                Model model) {
        Page<NTQCauHoi> cauHoiPage = cauHoiService.getAllQuestionsWithPagination(page, size);
        model.addAttribute("list", cauHoiPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", cauHoiPage.getTotalPages());
        return "admin/questions/index"; // Trả về trang danh sách câu hỏi
    }

    // Hiển thị form thêm câu hỏi
    @GetMapping("/questions/add")
    public String showAddForm(Model model) {
        model.addAttribute("question", new NTQCauHoi());
        return "admin/questions/add";
    }

    // Lưu câu hỏi (Thêm hoặc sửa)
    @PostMapping("/questions/save")
    public String saveQuestion(@ModelAttribute NTQCauHoi cauHoi) {
        cauHoiService.saveQuestion(cauHoi);
        return "redirect:/admin/questions";
    }

    // Hiển thị form sửa câu hỏi
    @GetMapping("/questions/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NTQCauHoi cauHoi = cauHoiService.getQuestionById(id);
        if (cauHoi != null) {
            model.addAttribute("question", cauHoi);
            return "admin/questions/edit";
        } else {
            return "redirect:/admin/questions";
        }
    }

    // Xóa câu hỏi
    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        cauHoiService.deleteQuestion(id);
        return "redirect:/admin/questions";
    }
}
