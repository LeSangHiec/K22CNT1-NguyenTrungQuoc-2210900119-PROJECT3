package com.example.services;

import com.example.models.NTQDanhMuc;
import com.example.repository.NTQDanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
public class NTQDanhMucService {

    @Autowired
    private NTQDanhMucRepository danhMucRepository;

    public Page<NTQDanhMuc> getCategoriesWithPagination(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return danhMucRepository.findAll(pageable);
    }
    public List<NTQDanhMuc> getAllCategories() {
        return danhMucRepository.findAll();
    }

    public Optional<NTQDanhMuc> getCategoryById(Long id) {
        return danhMucRepository.findById(id);
    }

    public void saveCategory(NTQDanhMuc danhMuc) {
        // Kiểm tra xem tên danh mục đã tồn tại chưa
        if (danhMucRepository.existsByTen(danhMuc.getTen())) {
            throw new IllegalArgumentException("Danh mục với tên này đã tồn tại.");
        }
        danhMucRepository.save(danhMuc);  // Lưu danh mục nếu chưa tồn tại
    }


    public void deleteCategory(Long id) {
        danhMucRepository.deleteById(id);
    }
}
