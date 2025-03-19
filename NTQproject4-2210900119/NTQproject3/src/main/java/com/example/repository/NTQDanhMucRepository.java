package com.example.repository;

import com.example.models.NTQDanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NTQDanhMucRepository extends JpaRepository<NTQDanhMuc, Long> {
    boolean existsByTen(String ten);  // Kiểm tra xem tên danh mục có tồn tại chưa
}
