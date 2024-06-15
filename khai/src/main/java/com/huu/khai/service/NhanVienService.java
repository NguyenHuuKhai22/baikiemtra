package com.huu.khai.service;
import com.huu.khai.model.NhanVien;
import com.huu.khai.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanvienRepository;
    // Retrieve all products from the database
    public List<NhanVien> getAllNhanViens() {
        return nhanvienRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<NhanVien> getNhanVienById(String id) {
        return nhanvienRepository.findById(id);
    }
    // Add a new product to the database
    public NhanVien addProduct(NhanVien nhanvien) {
        return nhanvienRepository.save(nhanvien);
    }
    // Update an existing product
    public NhanVien updateNhanVien(@NotNull NhanVien nhanvien) {
        NhanVien existingProduct = nhanvienRepository.findById(nhanvien.getMaNV())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        nhanvien.getMaNV() + " does not exist."));
        existingProduct.setTenNV(nhanvien.getTenNV());
        existingProduct.setPhai(nhanvien.getPhai());
        existingProduct.setNoiSinh(nhanvien.getNoiSinh());
        existingProduct.setPhongban(nhanvien.getPhongban());

        return nhanvienRepository.save(existingProduct);
    }
    // Delete a product by its id
    public void deleteNhanVienById(String id) {
        if (!nhanvienRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        nhanvienRepository.deleteById(id);
    }
}
