package com.example.controllers;

import com.example.models.NTQCauHoi;
import com.example.models.NTQNguoiDung;
import com.example.repository.NTQCauHoiRepository;
import com.example.repository.NTQNguoiDungRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserQuestionController {

    @Autowired
    private NTQCauHoiRepository cauHoiRepository;
    
    @Autowired
    private NTQNguoiDungRepository nguoiDungRepository;

    // Hiển thị trang danh sách câu hỏi
    @GetMapping("/questions")
    public String questionBank(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        // Giả sử bạn có phương thức trong service để lấy câu hỏi theo phân trang
        // model.addAttribute("list", cauHoiService.getAllQuestions(PageRequest.of(page, size)));
        model.addAttribute("list", cauHoiRepository.findAll(PageRequest.of(page, size)).getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", 5); // giả sử bạn tính được số trang
        return "fe/bank/question_bank";  // View hiển thị danh sách câu hỏi
    }

    // Hiển thị form thêm câu hỏi
    @GetMapping("/questions/add")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new NTQCauHoi());  // Tạo một đối tượng câu hỏi mới
        return "fe/bank/add_question";  // Chỉ đường dẫn đến file form add_question.html
    }

    // Xử lý form thêm câu hỏi
    @PostMapping("/questions/add")
    public String addQuestion(@ModelAttribute("question") NTQCauHoi question, HttpSession session) {
        // Lấy người dùng đang đăng nhập từ session
        NTQNguoiDung user = (NTQNguoiDung) session.getAttribute("loggedInUser");
        
        if (user != null) {
            // Gán người tạo là người dùng đang đăng nhập
            question.setNguoiTao(user);
            cauHoiRepository.save(question);  // Lưu câu hỏi vào database
        }
        return "redirect:/questions";  // Sau khi thêm câu hỏi xong, chuyển hướng về danh sách câu hỏi
    }
    @GetMapping("/questions/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, HttpSession session, Model model) {
        NTQCauHoi question = cauHoiRepository.findById(id).orElse(null);
        
        if (question == null) {
            return "redirect:/questions";  // Nếu không tìm thấy câu hỏi, chuyển hướng về danh sách câu hỏi
        }

        // Lấy người dùng từ session
        NTQNguoiDung loggedInUser = (NTQNguoiDung) session.getAttribute("loggedInUser");

        // Kiểm tra xem người dùng có phải là người tạo câu hỏi
        if (loggedInUser == null || !loggedInUser.getId().equals(question.getNguoiTao().getId())) {
            return "redirect:/questions";  // Nếu không phải người tạo, chuyển hướng về danh sách câu hỏi
        }

        model.addAttribute("question", question);
        return "fe/bank/edit_question";  // Chỉ đường dẫn đến file form edit_question.html
    }

    // Xử lý chỉnh sửa câu hỏi
    @PostMapping("/questions/edit/{id}")
    public String updateQuestion(@PathVariable("id") Long id, @ModelAttribute("question") NTQCauHoi updatedQuestion, HttpSession session) {
        NTQCauHoi question = cauHoiRepository.findById(id).orElse(null);

        if (question != null) {
            // Kiểm tra người dùng có phải là người tạo câu hỏi không
            NTQNguoiDung loggedInUser = (NTQNguoiDung) session.getAttribute("loggedInUser");
            if (loggedInUser == null || !loggedInUser.getId().equals(question.getNguoiTao().getId())) {
                return "redirect:/questions";  // Nếu không phải người tạo, chuyển hướng về danh sách câu hỏi
            }

            // Cập nhật thông tin câu hỏi
            question.setNoiDung(updatedQuestion.getNoiDung());
            question.setLoaiCauHoi(updatedQuestion.getLoaiCauHoi());
            question.setDiem(updatedQuestion.getDiem());
            question.setDapAnA(updatedQuestion.getDapAnA());
            question.setDapAnB(updatedQuestion.getDapAnB());
            question.setDapAnC(updatedQuestion.getDapAnC());
            question.setDapAnD(updatedQuestion.getDapAnD());
            question.setDapAnDung(updatedQuestion.getDapAnDung());

            cauHoiRepository.save(question);  // Lưu câu hỏi đã chỉnh sửa vào database
        }

        return "redirect:/questions";  // Sau khi chỉnh sửa xong, chuyển hướng về danh sách câu hỏi
    }


    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id, HttpSession session) {
        NTQCauHoi question = cauHoiRepository.findById(id).orElse(null);

        if (question != null) {
            // Kiểm tra người dùng có phải là người tạo câu hỏi không
            NTQNguoiDung loggedInUser = (NTQNguoiDung) session.getAttribute("loggedInUser");
            if (loggedInUser == null || !loggedInUser.getId().equals(question.getNguoiTao().getId())) {
                return "redirect:/questions";  // Nếu không phải người tạo, chuyển hướng về danh sách câu hỏi
            }

            cauHoiRepository.delete(question);  // Xóa câu hỏi khỏi database
        }

        return "redirect:/questions";  // Sau khi xóa câu hỏi, chuyển hướng về danh sách câu hỏi
    }
    @GetMapping("/questions/{id}")
    public String viewQuestionDetails(@PathVariable("id") Long id, HttpSession session, Model model) {
        // Lấy câu hỏi theo id từ cơ sở dữ liệu
        NTQCauHoi question = cauHoiRepository.findById(id).orElse(null);

        if (question == null) {
            return "redirect:/questions";  // Nếu không tìm thấy câu hỏi, chuyển hướng về danh sách câu hỏi
        }

        // Lấy người dùng từ session
        NTQNguoiDung loggedInUser = (NTQNguoiDung) session.getAttribute("loggedInUser");

        // Thêm câu hỏi và người tạo vào model
        model.addAttribute("question", question);
        model.addAttribute("creator", question.getNguoiTao());  // Người tạo câu hỏi

        return "fe/bank/question_details";  // Trả về trang chi tiết câu hỏi
    }


}

