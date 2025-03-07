package com.example.models;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "ntq_nguoi_dung")
public class NTQNguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@Column(nullable = false, unique = true)
    private String tenDangNhap;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String matKhau;

    @Enumerated(EnumType.STRING)
    private VaiTro vaiTro;

    @Enumerated(EnumType.STRING)
    private TrangThai trangThai;

    public enum VaiTro {
        HOC_SINH, GIAO_VIEN, QUAN_TRI
    }

    public enum TrangThai {
        HOAT_DONG, KHOA
    }
    
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

	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}

	public TrangThai getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}

	   
	public NTQNguoiDung() {
		super();
	}

	public NTQNguoiDung(Long id, String tenDangNhap, String email, String matKhau, VaiTro vaiTro, TrangThai trangThai) {
		super();
		this.id = id;
		this.tenDangNhap = tenDangNhap;
		this.email = email;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.trangThai = trangThai;
	}
}