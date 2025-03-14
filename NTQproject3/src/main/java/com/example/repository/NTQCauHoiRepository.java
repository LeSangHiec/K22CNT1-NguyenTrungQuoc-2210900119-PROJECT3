package com.example.repository;

import com.example.models.NTQCauHoi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NTQCauHoiRepository extends JpaRepository<NTQCauHoi, Long> {
    // Thêm các truy vấn tùy chỉnh nếu cần
    List<NTQCauHoi> findAll();
    

}
