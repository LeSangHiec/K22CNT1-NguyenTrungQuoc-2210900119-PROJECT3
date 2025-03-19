package com.example.models;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ntq_de_thi")
public class NTQDeThi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ntq_id")
    private Long id;

    @Column(name = "ntq_tieu_de", nullable = false)
    private String tieuDe;

    @Column(name = "ntq_mo_ta")
    private String moTa;

    // Liên kết đến danh mục (có thể null)
    @ManyToOne
    @JoinColumn(name = "ntq_danh_muc_id")
    private NTQDanhMuc danhMuc;

    // Người tạo đề thi (không được null)
    @ManyToOne
    @JoinColumn(name = "ntq_nguoi_tao_id", nullable = false)
    private NTQNguoiDung nguoiTao;

    @Column(name = "ntq_ngay_tao", nullable = false)
    private Timestamp ngayTao;

    // Quan hệ nhiều-nhiều với ngân hàng câu hỏi thông qua bảng ntq_de_thi_cau_hoi
    @ManyToMany
    @JoinTable(
        name = "ntq_de_thi_cau_hoi",
        joinColumns = @JoinColumn(name = "ntq_de_thi_id"),
        inverseJoinColumns = @JoinColumn(name = "ntq_cau_hoi_id")
    )
    private List<NTQCauHoi> cauHoiList = new ArrayList<>();

    // Getters và Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public NTQDanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(NTQDanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public NTQNguoiDung getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(NTQNguoiDung nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public List<NTQCauHoi> getCauHoiList() {
        return cauHoiList;
    }

    public void setCauHoiList(List<NTQCauHoi> cauHoiList) {
        this.cauHoiList = cauHoiList;
    }
    public void addCauHoi(NTQCauHoi cauHoi) {
        this.cauHoiList.add(cauHoi);
    }
}
