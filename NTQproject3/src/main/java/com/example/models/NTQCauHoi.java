package com.example.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class NTQCauHoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "de_thi_id",nullable = false)
    private NTQDeThi deThi;  // Chỉ ra rằng mỗi câu hỏi thuộc về một đề thi

    @Column(nullable = false)
    private String noiDung;

    @Enumerated(EnumType.STRING)
    private LoaiCauHoi loaiCauHoi;

    private double diem;

    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String dapAnDung;

    // Getter, Setter
    public enum LoaiCauHoi {
        TRAC_NGHIEM, // Câu hỏi trắc nghiệm
        DUNG_SAI,    // Câu hỏi đúng sai
        TU_LUAN      // Câu hỏi tự luận
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
}
