package com.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ntq_danh_muc")
public class NTQDanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ntq_id")
    private Long id;

    @Column(name = "ntq_ten", nullable = false, unique = true)
    private String ten;

    @Column(name = "ntq_mo_ta")
    private String moTa;

    public NTQDanhMuc() {
    }

    public NTQDanhMuc(Long id, String ten, String moTa) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
    }

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
}
