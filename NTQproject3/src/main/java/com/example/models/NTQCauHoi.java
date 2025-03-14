package com.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ntq_cau_hoi")
public class NTQCauHoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ntq_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ntq_nguoi_tao_id", nullable = false)
    private NTQNguoiDung nguoiTao;
    
    @Column(name = "ntq_noi_dung", nullable = false)
    private String noiDung;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ntq_loai_cau_hoi", nullable = false)
    private LoaiCauHoi loaiCauHoi;
    
    @Column(name = "ntq_diem", nullable = false)
    private double diem;
    
    @Lob
    @Column(name = "ntq_dap_an_a")
    private String dapAnA;
    
    @Lob
    @Column(name = "ntq_dap_an_b")
    private String dapAnB;
    
    @Lob
    @Column(name = "ntq_dap_an_c")
    private String dapAnC;
    
    @Lob
    @Column(name = "ntq_dap_an_d")
    private String dapAnD;
    
    @Column(name = "ntq_dap_an_dung", nullable = false)
    private String dapAnDung;

    // Getters và Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public NTQNguoiDung getNguoiTao() {
        return nguoiTao;
    }
    public void setNguoiTao(NTQNguoiDung nguoiTao) {
        this.nguoiTao = nguoiTao;
    }
    public String getNoiDung() {
        return noiDung;
    }
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    public LoaiCauHoi getLoaiCauHoi() {
        return loaiCauHoi;
    }
    public void setLoaiCauHoi(LoaiCauHoi loaiCauHoi) {
        this.loaiCauHoi = loaiCauHoi;
    }
    public double getDiem() {
        return diem;
    }
    public void setDiem(double diem) {
        this.diem = diem;
    }
    public String getDapAnA() {
        return dapAnA;
    }
    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }
    public String getDapAnB() {
        return dapAnB;
    }
    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }
    public String getDapAnC() {
        return dapAnC;
    }
    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }
    public String getDapAnD() {
        return dapAnD;
    }
    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }
    public String getDapAnDung() {
        return dapAnDung;
    }
    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }
    
    public String getDapAn() {
        // Trả về đáp án đúng từ cột dapAnDung
        return this.dapAnDung;
    }

}
