package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.dto.ResponseTotalTabungSampah;
import com.skripsi.waste_bank.models.TabungSampah;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TabungSampahService {
    ResponseEntity<ResponseData<TabungSampah>> createTabungSampah(TabungSampah tabungSampah );
    ResponseEntity<ResponseData<TabungSampah>> updateTabungSampah(
            Long idTabungSampah,
            TabungSampah tabungSampah );
    ResponseEntity<ResponseData<String>> deleteTabungSampah(Long id);
    ResponseEntity<ResponseData<List<TabungSampah>>> getAllTabungSampah();
    ResponseEntity<ResponseData<TabungSampah>> getTabungSampahById(Long id);
    ResponseEntity<ResponseData<List<TabungSampah>>> getTabungSampahByIdNasabah(Long idNasabah);
    ResponseEntity<ResponseData<ResponseTotalTabungSampah>> getTabungSampahsTotal();
    ResponseEntity<ResponseData<ResponseTotal>> getTabungSampahsTotalByIdNasabah(Long idNasabah);
}
