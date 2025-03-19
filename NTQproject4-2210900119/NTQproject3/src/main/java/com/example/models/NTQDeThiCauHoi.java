package com.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ntq_de_thi_cau_hoi")
public class NTQDeThiCauHoi {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Chỉ ra rằng cột này sẽ sử dụng AUTO_INCREMENT
    @Column(name = "ntq_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ntq_de_thi_id")
    private NTQDeThi deThi;  // Liên kết với bảng ntq_de_thi

    @ManyToOne
    @JoinColumn(name = "ntq_cau_hoi_id")
    private NTQCauHoi cauHoi;  // Liên kết với bảng ntq_cau_hoi

    // Constructors, Getters và Setters

    public NTQDeThiCauHoi() {
    }

    public NTQDeThiCauHoi(NTQDeThi deThi, NTQCauHoi cauHoi) {
        this.deThi = deThi;
        this.cauHoi = cauHoi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NTQDeThi getDeThi() {
        return deThi;
    }

    public void setDeThi(NTQDeThi deThi) {
        this.deThi = deThi;
    }

    public NTQCauHoi getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(NTQCauHoi cauHoi) {
        this.cauHoi = cauHoi;
    }
}
