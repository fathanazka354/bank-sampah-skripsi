package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public ResponseEntity<ResponseData<List<Admin>>> getAllAdmin();
    public ResponseEntity<ResponseData<Admin>> getAdminById(Long id);
    public ResponseEntity<ResponseData<Admin>> createAdmin(Admin admin);
    public ResponseEntity<String> updateAdmin(Long id, Admin admin);
    public ResponseEntity<String> deleteAdmin(Long id);
    public ResponseEntity<ResponseData<Admin>> login(String username, String email, String password);
}
