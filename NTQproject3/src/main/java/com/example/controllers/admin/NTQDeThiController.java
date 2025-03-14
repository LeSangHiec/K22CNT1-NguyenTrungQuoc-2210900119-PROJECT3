package com.example.controllers.admin;

import com.example.models.NTQDeThi;
import com.example.models.NTQCauHoi;
import com.example.models.NTQNguoiDung;
import com.example.services.NTQDeThiService;
import com.example.services.NTQCauHoiService;
import com.example.repository.NTQNguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/admin/exams")
public class NTQDeThiController {

    @Autowired
    private NTQDeThiService deThiService;

    // Dịch vụ câu hỏi – dùng để lấy danh sách câu hỏi cho form
    @Autowired
    private NTQCauHoiService cauHoiService;

    // Repository người dùng – giả sử dùng user có id = 1 làm người tạo
    @Autowired
    private NTQNguoiDungRepository nguoiDungRepository;

    @GetMapping
    public String listExams(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<NTQDeThi> examPage = deThiService.getAllDeThi(PageRequest.of(page, size));
        model.addAttribute("list", examPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", examPage.getTotalPages());
        return "admin/exams/index";
    }

    @GetMapping("/add")
    public String addExamForm(Model model) {
        model.addAttribute("exam", new NTQDeThi());
        // Lấy danh sách câu hỏi để chọn (giới hạn 100 câu hỏi cho demo)
        List<NTQCauHoi> questionList = cauHoiService.getAllQuestions(PageRequest.of(0, 100)).getContent();
        model.addAttribute("questionList", questionList);
        return "admin/exams/add";
    }

    @PostMapping("/add")
    public String saveExam(@ModelAttribute("exam") NTQDeThi exam, 
                           @RequestParam(value = "selectedQuestionIds", required = false) List<Long> selectedQuestionIds) {
        // Giả sử người tạo là user có id = 1 (cần đảm bảo user này tồn tại)
        NTQNguoiDung user = nguoiDungRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));
        exam.setNguoiTao(user);
        exam.setNgayTao(new Timestamp(System.currentTimeMillis()));

        // Gán danh sách câu hỏi nếu có
        if (selectedQuestionIds != null && !selectedQuestionIds.isEmpty()) {
            List<NTQCauHoi> selectedQuestions = cauHoiService.getQuestionsByIds(selectedQuestionIds);
            exam.setCauHoiList(selectedQuestions);
        }

        deThiService.saveDeThi(exam);
        return "redirect:/admin/exams";
    }

    @GetMapping("/edit/{id}")
    public String editExamForm(@PathVariable("id") Long id, Model model) {
        NTQDeThi exam = deThiService.getDeThiById(id);
        model.addAttribute("exam", exam);
        // Lấy danh sách câu hỏi để chọn
        List<NTQCauHoi> questionList = cauHoiService.getAllQuestions(PageRequest.of(0, 100)).getContent();
        model.addAttribute("questionList", questionList);
        return "admin/exams/edit";
    }

    @PostMapping("/edit")
    public String updateExam(@ModelAttribute("exam") NTQDeThi formExam, 
                             @RequestParam(value = "selectedQuestionIds", required = false) List<Long> selectedQuestionIds) {
        NTQDeThi existingExam = deThiService.getDeThiById(formExam.getId());
        if (existingExam == null) {
            throw new RuntimeException("Exam not found");
        }
        // Cập nhật các trường có thể thay đổi
        existingExam.setTieuDe(formExam.getTieuDe());
        existingExam.setMoTa(formExam.getMoTa());
        // Nếu cần, cập nhật danh mục

        // Cập nhật danh sách câu hỏi
        if (selectedQuestionIds != null && !selectedQuestionIds.isEmpty()) {
            List<NTQCauHoi> selectedQuestions = cauHoiService.getQuestionsByIds(selectedQuestionIds);
            existingExam.setCauHoiList(selectedQuestions);
        } else {
            existingExam.setCauHoiList(null);
        }

        deThiService.saveDeThi(existingExam);
        return "redirect:/admin/exams";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable("id") Long id) {
        deThiService.deleteDeThi(id);
        return "redirect:/admin/exams";
    }
}
