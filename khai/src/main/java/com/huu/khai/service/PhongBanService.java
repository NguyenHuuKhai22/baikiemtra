package com.huu.khai.service;
import com.huu.khai.model.PhongBan;
import com.huu.khai.repository.PhongBanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongbanRepository;
    /**
     * Retrieve all categories from the database.
     *
     * @return a list of categories
     */
    public List<PhongBan> getAllPhongBans() {
        return phongbanRepository.findAll();
    }

    public Optional<PhongBan> getPhongBanById(String id) {
        return phongbanRepository.findById(id);
    }

    public void addPhongBan(PhongBan phongban) {
        phongbanRepository.save(phongban);
    }

    public void updatePhongBan(@NotNull PhongBan phongban) {
        PhongBan existingPhongBan = phongbanRepository.findById(phongban.getMaPhong())
                .orElseThrow(() -> new IllegalStateException("PhongBan with ID " +
                        phongban.getMaPhong() + " does not exist."));
        existingPhongBan.setTenPhong(phongban.getTenPhong());
        phongbanRepository.save(existingPhongBan);
    }

    public void deletePhongBanById(String id) {
        if (!phongbanRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        phongbanRepository.deleteById(id);
    }
}
