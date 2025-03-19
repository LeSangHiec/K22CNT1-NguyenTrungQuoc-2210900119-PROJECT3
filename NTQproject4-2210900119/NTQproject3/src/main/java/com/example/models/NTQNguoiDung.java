package com.example.models;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ntq_nguoi_dung")
public class NTQNguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ntq_id")
    private Long id;

    @Column(name = "ntq_ten_dang_nhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "ntq_email", nullable = false, unique = true)
    private String email;

    @Column(name = "ntq_mat_khau", nullable = false)
    private String matKhau;

    // Sử dụng String cho vai trò; nếu cần, có thể chuyển sang Enum
    @Column(name = "ntq_vai_tro", nullable = false)
    private String vaiTro;

    @Column(name = "ntq_ngay_tao")
    private Timestamp ngayTao;

    // Sử dụng String cho trạng thái; nếu cần, có thể chuyển sang Enum
    @Column(name = "ntq_trang_thai")
    private String trangThai;

    // Constructor không đối số
    public NTQNguoiDung() {
    }

    // Constructor đầy đủ (nếu cần)
    public NTQNguoiDung(Long id, String tenDangNhap, String email, String matKhau, String vaiTro, Timestamp ngayTao, String trangThai) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.email = email;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    // Getters và Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
