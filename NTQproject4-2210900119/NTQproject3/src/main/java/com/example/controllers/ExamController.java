package com.example.controllers;

import com.example.models.NTQDeThi;
import com.example.models.NTQCauHoi;
import com.example.models.NTQNguoiDung;
import com.example.repository.NTQDeThiRepository;
import com.example.repository.NTQCauHoiRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private NTQDeThiRepository deThiRepository;

    @Autowired
    private NTQCauHoiRepository cauHoiRepository;

    // Hiển thị danh sách đề thi
    @GetMapping("/exam")
    public String viewExams(HttpSession session, Model model) {
        NTQNguoiDung user = (NTQNguoiDung) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<NTQDeThi> examList = deThiRepository.findByNguoiTaoId(user.getId());
        model.addAttribute("examList", examList);

        return "fe/bank/exam_list"; // Chỉnh lại tên file theo yêu cầu của bạn
    }

    // Hiển thị form thêm mới đề thi
    @GetMapping("/exam/add")
    public String showAddExamForm(Model model) {
        List<NTQCauHoi> allQuestions = cauHoiRepository.findAll();
        model.addAttribute("allQuestions", allQuestions);
        return "fe/bank/add_exam"; // Chỉnh lại tên file theo yêu cầu của bạn
    }

    // Xử lý thêm mới đề thi
    @PostMapping("/exam/add")
    public String addExam(@RequestParam("tieuDe") String tieuDe,
                          @RequestParam("moTa") String moTa,
                          @RequestParam("questions") List<Long> questionsId,
                          HttpSession session) {
        NTQNguoiDung user = (NTQNguoiDung) session.getAttribute("loggedInUser");

        NTQDeThi exam = new NTQDeThi();
        exam.setTieuDe(tieuDe);
        exam.setMoTa(moTa);
        exam.setNguoiTao(user);
        exam.setNgayTao(new Timestamp(0));
        // Lấy các câu hỏi từ database
        List<NTQCauHoi> selectedQuestions = cauHoiRepository.findAllById(questionsId);
        exam.setCauHoiList(selectedQuestions); // Liên kết các câu hỏi với đề thi

        deThiRepository.save(exam);

        return "redirect:/exam"; // Quay lại danh sách đề thi
    }

    // Hiển thị chi tiết đề thi
    @GetMapping("/exam/{id}/detail")
    public String viewExamDetail(@PathVariable("id") Long examId, Model model) {
        NTQDeThi exam = deThiRepository.findById(examId).orElse(null);
        if (exam == null) {
            return "redirect:/exam";
        }

        model.addAttribute("exam", exam);
        model.addAttribute("questions", exam.getCauHoiList()); // Hiển thị danh sách câu hỏi của đề thi

        return "fe/bank/exam_detail"; // Tạo trang để xem chi tiết đề thi
    }


    // Xóa đề thi
    @GetMapping("/exam/{id}/delete")
    public String deleteExam(@PathVariable("id") Long examId) {
        deThiRepository.deleteById(examId);
        return "redirect:/exam"; // Quay lại danh sách đề thi
    }

    // Chỉnh sửa đề thi (hiển thị form)
    @GetMapping("/exam/{id}/edit")
    public String showEditExamForm(@PathVariable Long id, Model model) {
        NTQDeThi exam = deThiRepository.findById(id).orElseThrow(() -> new RuntimeException("Exam not found"));
        List<NTQCauHoi> allQuestions = cauHoiRepository.findAll();
        model.addAttribute("exam", exam);
        model.addAttribute("allQuestions", allQuestions);
        return "fe/bank/edit_exam"; // Tạo trang edit_exam.html
    }

    // Xử lý chỉnh sửa đề thi
    @PostMapping("/exam/{id}/edit")
    public String editExam(@PathVariable Long id, 
                           @RequestParam("tieuDe") String tieuDe,
                           @RequestParam("moTa") String moTa, 
                           @RequestParam("questions") List<Long> questionsId) {
        NTQDeThi exam = deThiRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi"));

        exam.setTieuDe(tieuDe);
        exam.setMoTa(moTa);

        // Xử lý câu hỏi được chọn
        for (Long questionId : questionsId) {
            NTQCauHoi question = cauHoiRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi"));
            // Liên kết câu hỏi với đề thi
            // Cập nhật hoặc thêm câu hỏi vào đề thi nếu cần
        }

        deThiRepository.save(exam);

        return "redirect:/exam"; // Quay lại danh sách đề thi
    }


}
