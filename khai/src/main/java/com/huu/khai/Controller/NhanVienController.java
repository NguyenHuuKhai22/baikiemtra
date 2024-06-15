package com.huu.khai.Controller;
import com.huu.khai.model.NhanVien;
import com.huu.khai.service.PhongBanService;
import com.huu.khai.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/nhanviens")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanvienService;
    @Autowired
    private PhongBanService phongbanService; // Đảm bảo bạn đã inject

// Display a list of all products
    @GetMapping
    public String showNhanVienList(Model model) {
        model.addAttribute("nhanviens", nhanvienService.getAllNhanViens());
        return "/nhanviens/nhanviens-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongbans", phongbanService.getAllPhongBans());
        return "/nhanviens/add-nhanvien";
    }

    @PostMapping("/add")
    public String addNhanVien(@Valid NhanVien nhanvien, BindingResult result) {
        if (result.hasErrors()) {
            return "/nhanviens/add-nhanvien";
        }
        nhanvienService.addProduct(nhanvien);
        return "redirect:/nhanviens";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        NhanVien nhanvien = nhanvienService.getNhanVienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("nhanvien", nhanvien);
        model.addAttribute("categories", phongbanService.getAllPhongBans()); //Load categories
        return "/nhanviens/update-nhanvien";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable String id, @Valid NhanVien nhanvien,
                                BindingResult result) {
        if (result.hasErrors()) {
            nhanvien.setMaNV(id); // set id to keep it in the form in case of errors
            return "/nhanviens/update-nhanvien";
        }



        // Sau khi xử lý hình ảnh, cập nhật thông tin sản phẩm
        nhanvienService.updateNhanVien(nhanvien);
        return "redirect:/nhanviens";
    }

    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable String id) {
        nhanvienService.deleteNhanVienById(id);
        return "redirect:/nhanviens";
    }
}