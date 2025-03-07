// NTQDeThiCauHoi.java
package com.example.models;

import jakarta.persistence.*;

@Entity
public class NTQDeThiCauHoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "de_thi_id", nullable = false)
    private NTQDeThi deThi;

    @ManyToOne
    @JoinColumn(name = "cau_hoi_id", nullable = false)
    private NTQCauHoi cauHoi;

    // Getter and Setter cho cauHoi
    public NTQCauHoi getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(NTQCauHoi cauHoi) {
        this.cauHoi = cauHoi;
    }

    // Getter and Setter cho deThi
    public NTQDeThi getDeThi() {
        return deThi;
    }

    public void setDeThi(NTQDeThi deThi) {
        this.deThi = deThi;
    }
}
