package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.configuration.JwtService;
import com.skripsi.waste_bank.dto.AuthenticationResponse;
import com.skripsi.waste_bank.dto.RegisterRequest;
import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseToken;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.services.AdminService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import com.skripsi.waste_bank.utils.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired private AdminRepository adminRepository;
    @Autowired private MethodGenericService methodGenericService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private  JwtService jwtService;
    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Override
    public ResponseEntity<ResponseData<List<Admin>>> getAllAdmin(){
        return extractGetAllAdmin();
    }

    ResponseEntity<ResponseData<List<Admin>>> extractGetAllAdmin(){
        return methodGenericService.extractDataToResponse(adminRepository.findAll());
    }
    @Override
    public ResponseEntity<ResponseData<Admin>> getAdminById(Long id){
        if (!adminRepository.existsById(id)){
            return methodGenericService.extractDataToResponseSingle(false, null);
        }
        return methodGenericService.extractDataToResponseSingle(true, adminRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<ResponseData<Admin>> createAdmin(RegisterRequest request){
        List<Admin> admins = adminRepository.checkUserExists(request.getEmail());


        if (!admins.isEmpty()){
            logger.info("Data admin sudah ada");
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Username/Email Usdah terpakai"),"Data is not Created");
        }

        Admin admin = Admin.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .isActive(true)
                .role(Role.ADMIN)
                .build();

        adminRepository.saveAndFlush(admin);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(List.of(""),"Data Created");
    }

    @Override
    public ResponseEntity<ResponseData<ResponseToken>> updateAdmin(Long id, Admin admin) {
        Optional<Admin> adminOptional  = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            logger.info("Data admin tidak ditemukan");
            return methodGenericService.extractDataToResponseSingleCreateUpdate(List.of("Data Admin tidak ditemukan"), "Data is not Updated");
        }
        if (admin.getPassword() != null && admin.getPassword().length() < 6){
            logger.info("Password < 6");
            return methodGenericService.extractDataToResponseSingleCreateUpdate(List.of("Password kurang dari 6 character"), "Data is not Updated");
        }

        int result = adminRepository.updateAdmin(
                admin.getFirstName() == null ? adminOptional.get().getFirstName():admin.getFirstName(),
                admin.getLastName() == null ? adminOptional.get().getLastName():admin.getLastName(),
                admin.getPassword() == null ? adminOptional.get().getPassword(): passwordEncoder.encode(admin.getPassword()),
                admin.getEmail() == null ? adminOptional.get().getEmail():admin.getEmail(),
                Objects.equals(admin.getImgUrl(), "") ? adminOptional.get().getImgUrl():admin.getImgUrl(),
                adminOptional.get().getIdAdmin());
        logger.info("Data Updated {}",admin.getEmail());
        if (result > 0){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(List.of(""), "Data successfully to update");
        }
        return methodGenericService.extractDataToResponseSingleCreateUpdate(List.of("Data failed to update"), "Data is not Updated");
    }

    @Override
    public ResponseEntity<ResponseData<String>> deleteAdmin(Long id){
        if (adminRepository.existsById(id)) {
            logger.info("Data admin tidak ada");
            return methodGenericService.extractDataToResponseDelete(false);
        }
        var result = adminRepository.deleteAdmin(id);
        if (result > 0){
            return methodGenericService.extractDataToResponseDelete(true);
        }
        return methodGenericService.extractDataToResponseDelete(false);
    }

    @Override
    public ResponseEntity<ResponseData<AuthenticationResponse>> login(String username, String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        var user = adminRepository.findByEmail(email).orElseThrow();
        var jwt = jwtService.generateToken(user);
        var response = AuthenticationResponse.builder().email(email).token(jwt).build();
            return methodGenericService.extractDataToResponseSingle(true,response);
    }

    @Override
    public ResponseEntity<ResponseData<Admin>> findByEmail(String email) {
        Optional<Admin> byEmail = adminRepository.findByEmail(email);
        if (byEmail.isEmpty()){
            return methodGenericService.extractDataToResponseSingle(false, null);
        }
        return methodGenericService.extractDataToResponseSingle(true, byEmail.get());
    }

}
