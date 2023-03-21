package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Nasabah;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NasabahService {
    ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabah();
    ResponseEntity<ResponseData<Nasabah>> getNasabahById(Long id);
    ResponseEntity<ResponseData<String>> deleteNasabahById(Long id);
    ResponseEntity<ResponseData<Nasabah>> updateNasabahById(Long id, Nasabah nasabah);
    ResponseEntity<ResponseData<Nasabah>> createNasabah(Nasabah nasabah);
    ResponseEntity<ResponseData<Nasabah>> login(String username,String email, String password);
}
