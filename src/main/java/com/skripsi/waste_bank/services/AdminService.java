package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.RegisterRequest;
import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseToken;
import com.skripsi.waste_bank.models.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public ResponseEntity<ResponseData<List<Admin>>> getAllAdmin();
    public ResponseEntity<ResponseData<Admin>> getAdminById(Long id);
    public ResponseEntity<ResponseData<Admin>> createAdmin(RegisterRequest admin);
    public ResponseEntity<ResponseData<ResponseToken>> updateAdmin(Long id, Admin admin);
    public ResponseEntity<ResponseData<String>> deleteAdmin(Long id);
    public ResponseEntity<ResponseData<ResponseToken>> login(String username, String email, String password);
}
