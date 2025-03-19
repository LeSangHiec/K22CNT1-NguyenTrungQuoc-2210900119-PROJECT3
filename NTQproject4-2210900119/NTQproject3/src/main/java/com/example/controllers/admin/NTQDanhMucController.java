package com.example.controllers.admin;

import com.example.models.NTQDanhMuc;
import com.example.services.NTQDanhMucService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class NTQDanhMucController {

    @Autowired
    private NTQDanhMucService danhMucService;

    // Hiển thị danh sách danh mục với phân trang
    @GetMapping("/category")
    public String listCategories(@RequestParam(value = "page", defaultValue = "0") int page, 
                                 @RequestParam(value = "size", defaultValue = "5") int size, 
                                 Model model) {
        // Lấy danh sách danh mục với phân trang
        Page<NTQDanhMuc> danhMucPage = danhMucService.getCategoriesWithPagination(page, size);

        model.addAttribute("list", danhMucPage.getContent()); // Lấy nội dung trang hiện tại
        model.addAttribute("currentPage", page); // Trang hiện tại
        model.addAttribute("totalPages", danhMucPage.getTotalPages()); // Tổng số trang
        return "admin/category/index"; // Trả về trang index
    }

    // Hiển thị form thêm danh mục
    @GetMapping("/category/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new NTQDanhMuc()); // Tạo đối tượng danh mục trống
        return "admin/category/add"; // Trả về trang add.html trong thư mục templates/admin/category
    }


    // Lưu danh mục (Thêm/Sửa)
    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute NTQDanhMuc danhMuc, Model model) {
        try {
            danhMucService.saveCategory(danhMuc);  // Lưu danh mục
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());  // Thêm thông báo lỗi vào model
            model.addAttribute("category", danhMuc);  // Truyền lại dữ liệu vào form
            return "admin/category/add";  // Quay lại trang thêm danh mục
        }
        return "redirect:/admin/category";  // Quay lại trang danh mục sau khi lưu
    }



    // Hiển thị form sửa danh mục
    @GetMapping("/category/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<NTQDanhMuc> danhMuc = danhMucService.getCategoryById(id); // Lấy danh mục theo ID
        if (danhMuc.isPresent()) {
            model.addAttribute("category", danhMuc.get());  // Truyền đối tượng danh mục vào model
            return "admin/category/edit";  // Trả về trang edit.html
        } else {
            return "redirect:/admin/category";  // Nếu không tìm thấy danh mục, quay lại trang danh mục
        }
    }


    // Xóa danh mục
    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        danhMucService.deleteCategory(id);  // Xóa danh mục
        return "redirect:/admin/category";  // Quay lại trang danh mục sau khi xóa
    }
}

