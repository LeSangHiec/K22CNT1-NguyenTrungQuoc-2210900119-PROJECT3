package com.example.services;

import com.example.models.NTQNguoiDung;
import com.example.repository.NTQNguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NTQNguoiDungService {

    @Autowired
    private NTQNguoiDungRepository nguoiDungRepository;

    // Lấy danh sách người dùng với phân trang
    public Page<NTQNguoiDung> getAllUsersWithPagination(int page, int size) {
        return nguoiDungRepository.findAll(PageRequest.of(page, size));
    }

    // Lấy người dùng theo ID
    public Optional<NTQNguoiDung> getUserById(Long id) {
        return nguoiDungRepository.findById(id);
    }

    // Lưu người dùng (thêm/sửa)
    public void saveUser(NTQNguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }

    // Xóa người dùng
    public void deleteUser(Long id) {
        nguoiDungRepository.deleteById(id);
    }
}
