package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.services.AdminService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired private AdminRepository adminRepository;
    @Autowired private MethodGenericService methodGenericService;
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
        if (adminRepository.existsById(id)){
            return methodGenericService.extractDataToResponseSingle(false, null);
        }
        return methodGenericService.extractDataToResponseSingle(true, adminRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<ResponseData<Admin>> createAdmin(Admin admin){
        List<Admin> admins = adminRepository.checkUserExists(admin.getUsername(), admin.getEmail());


        if (!admins.isEmpty()){
            logger.info("Data admin sudah ada");
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Username/Email Usdah terpakai"),"Data is not Created");
        }

        adminRepository.saveAndFlush(admin);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Created");
    }

    @Override
    public ResponseEntity<ResponseData<String>> updateAdmin(Long id, Admin admin) {
        Optional<Admin> adminOptional  = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            logger.info("Data admin tidak ditemukan");
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data Admin tidak ditemukan"), "Data is not Updated");
        }
        if (admin.getPassword() != null && admin.getPassword().length() < 6){
            logger.info("Password < 6");
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Password kurang dari 6 character"), "Data is not Updated");
        }
        List<Admin> admins = adminRepository.checkUserExists(admin.getUsername(), admin.getEmail());


        if (!admins.isEmpty()){
            logger.info("Data admin sudah ada");
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data username/email sudah terpakai"), "Data is not Updated");
        }
        int result = adminRepository.updateAdmin(
                admin.getUsername() == null ? adminOptional.get().getUsername():admin.getUsername(),
                admin.getPassword() == null ? adminOptional.get().getPassword():admin.getPassword(),
                admin.getEmail() == null ? adminOptional.get().getEmail():admin.getEmail(),
                Objects.equals(admin.getImgUrl(), "") ? adminOptional.get().getImgUrl():admin.getImgUrl(),
                adminOptional.get().getIdAdmin());
        logger.info("Data Updated {}",result);
        if (result > 0){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""), "Data Updated");
        }
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data failed to update"), "Data is not Updated");
    }

    ResponseEntity<String> extractUpdateAdmin(Long id, Admin admin){
        Optional<Admin> adminOptional  = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            logger.info("Data admin tidak ditemukan");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (admin.getPassword() != null && admin.getPassword().length() < 6){
            logger.info("Password < 6");
            return new ResponseEntity<>("Password Kurang dari 6 character",HttpStatus.BAD_REQUEST);
        }
        List<Admin> admins = adminRepository.checkUserExists(admin.getUsername(), admin.getEmail());


        if (!admins.isEmpty()){
            logger.info("Data admin sudah ada");
            return new ResponseEntity<>("Data Admin Sudah ada",HttpStatus.BAD_REQUEST);
        }
        int result = adminRepository.updateAdmin(
                admin.getUsername() == null ? adminOptional.get().getUsername():admin.getUsername(),
                admin.getPassword() == null ? adminOptional.get().getPassword():admin.getPassword(),
                admin.getEmail() == null ? adminOptional.get().getEmail():admin.getEmail(),
                Objects.equals(admin.getImgUrl(), "") ? adminOptional.get().getImgUrl():admin.getImgUrl(),
                adminOptional.get().getIdAdmin());
        logger.info("Data Updated {}",result);
        if (result > 0){
            return new ResponseEntity<>("Data Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data can not Updated", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<ResponseData<Admin>> login(String username, String email, String password) {
        List<Admin> login = adminRepository.login(username, email, password);
        if (!login.isEmpty()){
            return methodGenericService.extractDataToResponseSingle(true,login.get(0));
        }
        return methodGenericService.extractDataToResponseSingle(false,null);
    }

}
