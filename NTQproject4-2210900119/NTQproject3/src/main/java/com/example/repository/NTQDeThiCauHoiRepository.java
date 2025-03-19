package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.models.NTQDeThiCauHoi;

@Repository
public interface NTQDeThiCauHoiRepository extends JpaRepository<NTQDeThiCauHoi, Long> {
}
