package com.example.services;

import com.example.models.NTQDeThi;
import com.example.models.NTQCauHoi;
import com.example.models.NTQDeThiCauHoi;
import com.example.repository.NTQDeThiCauHoiRepository;
import com.example.repository.NTQDeThiRepository;
import com.example.repository.NTQCauHoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NTQDeThiCauHoiService {

    @Autowired
    private NTQDeThiCauHoiRepository deThiCauHoiRepository;

    @Autowired
    private NTQDeThiService deThiService;

    @Autowired
    private NTQCauHoiRepository cauHoiRepository;

    @Autowired
    private NTQDeThiRepository deThiRepository;
    public void addCauHoiToDeThi(Long deThiId, Long cauHoiId) {
        NTQDeThi deThi = deThiService.getDeThiById(deThiId);
        NTQCauHoi cauHoi = cauHoiRepository.findById(cauHoiId).orElse(null);
        if (deThi != null && cauHoi != null) {
            NTQDeThiCauHoi deThiCauHoi = new NTQDeThiCauHoi();
            deThiCauHoi.setDeThi(deThi);
            deThiCauHoi.setCauHoi(cauHoi);
            deThiCauHoiRepository.save(deThiCauHoi);
        }
    }

    public void removeCauHoiFromDeThi(Long deThiId, Long cauHoiId) {
        NTQDeThiCauHoi deThiCauHoi = deThiCauHoiRepository.findByDeThiIdAndCauHoiId(deThiId, cauHoiId);
        if (deThiCauHoi != null) {
            deThiCauHoiRepository.delete(deThiCauHoi);
        }
    }
    public Optional<NTQDeThi> getDeThiById(Long id) {
        return deThiRepository.findById(id);  // Truyền id để lấy Đề Thi
    }
    
}
