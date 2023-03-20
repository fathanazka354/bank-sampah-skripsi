package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.models.AmbilTabungan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmbilTabunganService {
    List<AmbilTabungan> getAllAmbilTabungan();
    AmbilTabungan getAmbilTabunganById(Long id);
    AmbilTabungan createTabungan(AmbilTabungan ambilTabungan,Long idNasabah);
    AmbilTabungan updateTabungan(AmbilTabungan ambilTabungan, Long idNasabah);
    String deleteAmbilTabungan(Long idAmbilTabungan);
}
