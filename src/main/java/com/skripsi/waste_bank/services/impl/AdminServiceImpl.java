package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.services.AdminService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        ResponseData responseData = new ResponseData();
        responseData.setData(adminRepository.findAll());
        responseData.setStatus("Success");
        return new ResponseEntity(responseData, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ResponseData<Admin>> getAdminById(Long id){
        return extractGetAdminById(id);
    }

    ResponseEntity<ResponseData<Admin>> extractGetAdminById(Long id){
        ResponseData responseData = new ResponseData();

        if (adminRepository.findAll().isEmpty()){
            logger.info("Admin is empty");
            responseData.setData(null);
            responseData.setStatus("Failed");
            return new ResponseEntity(responseData,HttpStatus.NO_CONTENT);
        }
        responseData.setData(adminRepository.findById(id).get());
        responseData.setStatus("Success");
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseData<Admin>> createAdmin(Admin admin){
        return extractCreateAdmin(admin);
    }

    ResponseEntity<ResponseData<Admin>> extractCreateAdmin(Admin admin){
        ResponseData responseData = new ResponseData();

        List<Admin> admins = adminRepository.checkUserExists(admin.getUsername(), admin.getEmail());


        if (!admins.isEmpty()){
            logger.info("Data admin sudah ada");
            responseData.setData(null);
            responseData.setStatus("Failed");
            return new ResponseEntity<>(responseData,HttpStatus.BAD_REQUEST);
        }
        adminRepository.saveAndFlush(admin);
        responseData.setData("Data Created");
        responseData.setStatus("Success");
        return new ResponseEntity<>(responseData, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateAdmin(Long id, Admin admin) {
        return extractUpdateAdmin(id, admin);
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
                admin.getImgUrl() == null ? adminOptional.get().getImgUrl():admin.getImgUrl(),
                adminOptional.get().getIdAdmin());
        logger.info("Data Updated {}",result);
        if (result > 0){
            return new ResponseEntity<>("Data Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data can not Updated", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteAdmin(Long id){
        return extractDeleteAdmin(id);
    }

    @Override
    public ResponseEntity<ResponseData<Admin>> login(String username, String email, String password) {
        List<Admin> login = adminRepository.login(username, email, password);
        if (!login.isEmpty()){
            return methodGenericService.extractDataToResponseSingle(true,login.get(0));
        }
        return methodGenericService.extractDataToResponseSingle(false,null);
    }

    ResponseEntity<String> extractDeleteAdmin(Long id){
        Optional<Admin> admin  = adminRepository.findById(id);

        if (admin.isEmpty()) {
            logger.info("Data admin tidak ada");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        adminRepository.deleteAdmin(admin.get().getIdAdmin());
        return new ResponseEntity<>("Admin deleted", HttpStatus.NOT_FOUND);
    }

}
