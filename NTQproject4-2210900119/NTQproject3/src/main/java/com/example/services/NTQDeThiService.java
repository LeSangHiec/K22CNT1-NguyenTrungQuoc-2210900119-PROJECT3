package com.example.services;

import com.example.models.NTQDeThi;
import com.example.repository.NTQDeThiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NTQDeThiService {

    @Autowired
    private NTQDeThiRepository deThiRepository;

    public Page<NTQDeThi> getAllDeThi(Pageable pageable) {
        return deThiRepository.findAll(pageable);
    }

    public NTQDeThi getDeThiById(Long id) {
        return deThiRepository.findById(id).orElse(null);
    }

    public NTQDeThi saveDeThi(NTQDeThi deThi) {
        return deThiRepository.save(deThi);
    }

    public void deleteDeThi(Long id) {
        deThiRepository.deleteById(id);
    }
}
