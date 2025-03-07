package com.example.repository;

import com.example.models.NTQDeThiCauHoi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NTQDeThiCauHoiRepository extends JpaRepository<NTQDeThiCauHoi, Long> {
    NTQDeThiCauHoi findByDeThiIdAndCauHoiId(Long deThiId, Long cauHoiId);
}
