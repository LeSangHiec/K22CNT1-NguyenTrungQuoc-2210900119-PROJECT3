package com.example.services;

import com.example.models.NTQDeThi;
import com.example.repository.NTQDeThiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NTQDeThiService {

    @Autowired
    private NTQDeThiRepository deThiRepository;

    public List<NTQDeThi> getAllDeThi() {
        return deThiRepository.findAll();
    }

    public NTQDeThi getDeThiById(Long id) {
        return deThiRepository.findById(id).orElse(null);
    }

    public void saveDeThi(NTQDeThi deThi) {
        deThiRepository.save(deThi);
    }

    public void deleteDeThi(Long id) {
        deThiRepository.deleteById(id);
    }
}

