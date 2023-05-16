package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.models.AmbilTabungan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AmbilTabunganService {
    ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabungan();
    ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabunganByIdNasabah(Long idNasabah);
    ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabunganByTanggal(Date createdAt, Date updatedAt);
    ResponseEntity<ResponseData<AmbilTabungan>> getAmbilTabunganById(Long id);
    ResponseEntity<ResponseData<ResponseTotal>> getAmbilTabungansTotal();
    ResponseEntity<ResponseData<ResponseTotal>> getAmbilTabungansTotalByIdNasabah(Long idNasabah);
    ResponseEntity<ResponseData<AmbilTabungan>> createTabungan(AmbilTabungan ambilTabungan,Long idNasabah, Long idAdmin);
    ResponseEntity<ResponseData<AmbilTabungan>> updateTabungan(AmbilTabungan ambilTabungan, Long id, Long idAdmin, Long idNasabah);
    ResponseEntity<ResponseData<String>> deleteAmbilTabungan(Long idAmbilTabungan);
}
