package com.example.services;

import com.example.models.NTQCauHoi;
import com.example.repository.NTQCauHoiRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NTQCauHoiService {

    @Autowired
    private NTQCauHoiRepository cauHoiRepository;

    // Lấy tất cả câu hỏi với phân trang
    public Page<NTQCauHoi> getAllQuestionsWithPagination(int page, int size) {
        return cauHoiRepository.findAll(PageRequest.of(page, size));
    }

    // Lưu câu hỏi (Thêm hoặc sửa)
    public NTQCauHoi saveQuestion(NTQCauHoi cauHoi) {
        return cauHoiRepository.save(cauHoi);
    }

    // Lấy câu hỏi theo ID
    public NTQCauHoi getQuestionById(Long id) {
        return cauHoiRepository.findById(id).orElse(null);
    }

    // Xóa câu hỏi
    public void deleteQuestion(Long id) {
        cauHoiRepository.deleteById(id);
    }

    public List<NTQCauHoi> getAllCauHoi() {
        return cauHoiRepository.findAll();  // Giả sử bạn dùng Spring Data JPA
    }
    
}
