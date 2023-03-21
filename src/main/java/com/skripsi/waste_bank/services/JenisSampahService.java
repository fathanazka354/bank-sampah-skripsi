package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.JenisSampah;
import com.skripsi.waste_bank.utils.JenisSampahValue;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JenisSampahService{
    ResponseEntity<ResponseData<List<JenisSampah>>> getAllJenisSampah();
    ResponseEntity<ResponseData<JenisSampah>> getJenisSampahById(Long id);
    ResponseEntity<ResponseData<JenisSampah>> createJenisSampah(JenisSampah jenisSampah);
    ResponseEntity<ResponseData<JenisSampah>> updateJenisSampah(Long id, JenisSampah jenisSampah);
}
