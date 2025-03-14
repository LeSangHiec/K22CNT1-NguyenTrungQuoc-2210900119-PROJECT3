package com.example.controllers.admin;

import com.example.models.NTQCauHoi;
import com.example.models.NTQNguoiDung;
import com.example.repository.NTQNguoiDungRepository;
import com.example.services.NTQCauHoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/questions")
public class NTQCauHoiController {

    // Đảm bảo đã khai báo biến và import đúng class NTQCauHoiService
    @Autowired
    private NTQCauHoiService ntqCauHoiService;

    // Nếu cần sử dụng repository để lấy user hiện hành
    @Autowired
    private NTQNguoiDungRepository ntqNguoiDungRepository;

    @GetMapping
    public String listQuestions(Model model,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<NTQCauHoi> questionPage = ntqCauHoiService.getAllQuestions(PageRequest.of(page, size));
        model.addAttribute("list", questionPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", questionPage.getTotalPages());
        return "admin/questions/index";
    }

    @GetMapping("/add")
    public String addQuestionForm(Model model) {
        model.addAttribute("question", new NTQCauHoi());
        return "admin/questions/add";
    }

    @PostMapping("/add")
    public String saveQuestion(@ModelAttribute("question") NTQCauHoi question) {
        // Giả sử bạn đang gán user có id=1 làm người tạo, cần đảm bảo user này tồn tại
        NTQNguoiDung user = ntqNguoiDungRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("User not found"));
        question.setNguoiTao(user);
        ntqCauHoiService.saveQuestion(question);
        return "redirect:/admin/questions";
    }

    @GetMapping("/edit/{id}")
    public String editQuestionForm(@PathVariable("id") Long id, Model model) {
        NTQCauHoi question = ntqCauHoiService.getQuestionById(id);
        model.addAttribute("question", question);
        return "admin/questions/edit";
    }

    @PostMapping("/edit")
    public String updateQuestion(@ModelAttribute("question") NTQCauHoi formQuestion) {
        // Lấy đối tượng gốc từ CSDL
        NTQCauHoi existingQuestion = ntqCauHoiService.getQuestionById(formQuestion.getId());
        
        if (existingQuestion == null) {
            throw new RuntimeException("Question not found");
        }
        
        // Cập nhật các trường từ form, giữ lại người tạo (nguoiTao)
        existingQuestion.setNoiDung(formQuestion.getNoiDung());
        existingQuestion.setLoaiCauHoi(formQuestion.getLoaiCauHoi());
        existingQuestion.setDiem(formQuestion.getDiem());
        existingQuestion.setDapAnA(formQuestion.getDapAnA());
        existingQuestion.setDapAnB(formQuestion.getDapAnB());
        existingQuestion.setDapAnC(formQuestion.getDapAnC());
        existingQuestion.setDapAnD(formQuestion.getDapAnD());
        existingQuestion.setDapAnDung(formQuestion.getDapAnDung());
        
        // Lưu đối tượng đã cập nhật
        ntqCauHoiService.saveQuestion(existingQuestion);
        return "redirect:/admin/questions";
    }


    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) {
        ntqCauHoiService.deleteQuestion(id);
        return "redirect:/admin/questions";
    }
}
