package com.example.models;

import jakarta.persistence.*;



@Entity
@Table(name = "NTQ_danh_muc")
public class NTQDanhMuc {
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public NTQDanhMuc(Long id, String ten, String moTa) {
		super();
		this.id = id;
		this.ten = ten;
		this.moTa = moTa;
	}

	public NTQDanhMuc() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Đảm bảo tên biến là `id`

    @Column(nullable = false, unique = true)
    private String ten;

    private String moTa;
}

