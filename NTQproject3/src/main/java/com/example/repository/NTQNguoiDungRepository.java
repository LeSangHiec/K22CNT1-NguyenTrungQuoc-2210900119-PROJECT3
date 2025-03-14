package com.example.repository;

import com.example.models.NTQNguoiDung;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NTQNguoiDungRepository extends JpaRepository<NTQNguoiDung, Long> {
	Optional<NTQNguoiDung> findByTenDangNhap(String tenDangNhap);
	
}
