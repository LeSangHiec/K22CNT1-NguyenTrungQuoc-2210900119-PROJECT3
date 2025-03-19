package com.example.services;

import com.example.models.NTQCauHoi;
import com.example.repository.NTQCauHoiRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class NTQCauHoiService {

    @Autowired
    private NTQCauHoiRepository repository;
    
    public Page<NTQCauHoi> getAllQuestions(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    public NTQCauHoi getQuestionById(Long id) {
        return repository.findById(id).orElse(null);
    } 
    
    public NTQCauHoi saveQuestion(NTQCauHoi question) {
        return repository.save(question);
    }
    
    public void deleteQuestion(Long id) {
        repository.deleteById(id);
    }
    // Phương thức lấy danh sách câu hỏi dựa trên danh sách id
    public List<NTQCauHoi> getQuestionsByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
    
}