package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.*;
import com.skripsi.waste_bank.models.Nasabah;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NasabahService {
    ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabah();
    ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabahActive();
    ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabahNotActive();
    ResponseEntity<ResponseData<Nasabah>> getNasabahById(Long id);
    ResponseEntity<ResponseData<ResponseTotal>> getNasabahsTotal();
    ResponseEntity<ResponseData<String>> deleteNasabahById(Long id);
    ResponseEntity<ResponseData<String>> updateNasabahById(Long id, Nasabah nasabah);
    ResponseEntity<ResponseData<String>> updateTabunganNasabahById(Long id, double saldo);
    ResponseEntity<ResponseData<Nasabah>> createNasabah(NasabahDTO nasabah);
    ResponseEntity<ResponseData<AuthenticationResponse>> login(String email, String password);
    ResponseEntity<ResponseData<Nasabah>> getByEmail( String email);
}
