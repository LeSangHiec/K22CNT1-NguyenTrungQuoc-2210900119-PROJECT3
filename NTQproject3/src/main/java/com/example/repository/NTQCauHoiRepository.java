package com.example.repository;

import com.example.models.NTQCauHoi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NTQCauHoiRepository extends JpaRepository<NTQCauHoi, Long> {
    // Các phương thức tùy chỉnh nếu cần
}
