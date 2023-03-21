package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.AmbilTabungan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmbilTabunganService {
    ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabungan();
    ResponseEntity<ResponseData<AmbilTabungan>> getAmbilTabunganById(Long id);
    ResponseEntity<ResponseData<AmbilTabungan>> createTabungan(AmbilTabungan ambilTabungan,Long idNasabah, Long idAdmin);
    ResponseEntity<ResponseData<AmbilTabungan>> updateTabungan(AmbilTabungan ambilTabungan, Long id);
    ResponseEntity<ResponseData<String>> deleteAmbilTabungan(Long idAmbilTabungan);
}
