package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class NTQDeThi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tieuDe;
    private String moTa;

    @OneToMany(mappedBy = "deThi", cascade = CascadeType.ALL)
    private List<NTQCauHoi> cauHoiList;  // Chứa danh sách các câu hỏi trong đề thi

    // Các trường khác và getter, setter
}