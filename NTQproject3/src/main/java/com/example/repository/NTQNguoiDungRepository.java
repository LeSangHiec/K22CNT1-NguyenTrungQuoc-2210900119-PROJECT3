package com.example.repository;

import com.example.models.NTQNguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NTQNguoiDungRepository extends JpaRepository<NTQNguoiDung, Long> {
    Optional<NTQNguoiDung> findByTenDangNhap(String tenDangNhap);
}
