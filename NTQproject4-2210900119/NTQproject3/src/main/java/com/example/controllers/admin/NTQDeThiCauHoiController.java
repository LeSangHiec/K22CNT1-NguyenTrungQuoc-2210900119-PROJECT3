package com.example.controllers.admin;

import com.example.models.NTQDeThi;
import com.example.models.NTQCauHoi;
import com.example.services.NTQDeThiCauHoiService;
import com.example.services.NTQCauHoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class NTQDeThiCauHoiController {

    @Autowired
    private NTQDeThiCauHoiService deThiCauHoiService;

    @Autowired
    private NTQCauHoiService cauHoiService;

    // Thêm câu hỏi vào đề thi
    @GetMapping("/de-thi/{deThiId}/add-cau-hoi")
    public String showAddCauHoiForm(@PathVariable Long deThiId, Model model) {
        Optional<NTQDeThi> deThi = deThiCauHoiService.getDeThiById(deThiId);
        List<NTQCauHoi> cauHoiList = cauHoiService.getAllCauHoi();
        model.addAttribute("deThi", deThi);
        model.addAttribute("cauHoiList", cauHoiList);
        return "admin/de-thi/add-cau-hoi";
    }

    // Lưu câu hỏi vào đề thi
    @PostMapping("/de-thi/add-cau-hoi")
    public String saveCauHoiToDeThi(@RequestParam Long deThiId, @RequestParam Long cauHoiId) {
        deThiCauHoiService.addCauHoiToDeThi(deThiId, cauHoiId);
        return "redirect:/admin/de-thi";
    }

    // Xóa câu hỏi khỏi đề thi
    @GetMapping("/de-thi/{deThiId}/remove-cau-hoi/{cauHoiId}")
    public String removeCauHoiFromDeThi(@PathVariable Long deThiId, @PathVariable Long cauHoiId) {
        deThiCauHoiService.removeCauHoiFromDeThi(deThiId, cauHoiId);
        return "redirect:/admin/de-thi";
    }
}
