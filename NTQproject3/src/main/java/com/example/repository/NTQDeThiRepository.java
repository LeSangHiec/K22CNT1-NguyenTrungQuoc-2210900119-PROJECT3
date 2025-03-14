package com.example.repository;

import com.example.models.NTQDeThi;
import com.example.models.NTQNguoiDung;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NTQDeThiRepository extends JpaRepository<NTQDeThi, Long> {
    List<NTQDeThi> findByNguoiTao(NTQNguoiDung nguoiTao);

    List<NTQDeThi> findByNguoiTaoId(Long nguoiTaoId); // Phương thức này giúp tìm đề thi của người tạo


}
