package com.example.services;

import com.example.models.NTQNguoiDung;
import com.example.repository.NTQNguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private NTQNguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NTQNguoiDung user = nguoiDungRepository.findByTenDangNhap(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        // Giả sử vai trò được lưu dưới dạng chuỗi, ví dụ "QUAN_TRI", "GIAO_VIEN", "HOC_SINH"
        // Bạn có thể chuyển đổi thành GrantedAuthority như sau:
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getVaiTro());
        
        return new org.springframework.security.core.userdetails.User(
                user.getTenDangNhap(),
                user.getMatKhau(),
                Collections.singletonList(authority)
        );
    }
}
