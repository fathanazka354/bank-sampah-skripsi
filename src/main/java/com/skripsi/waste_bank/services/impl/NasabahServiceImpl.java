package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.services.NasabahService;
import com.skripsi.waste_bank.utils.EmailValidation;
import com.skripsi.waste_bank.utils.MethodGenericService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NasabahServiceImpl implements NasabahService {
    private NasabahRepository nasabahRepository;
    private MethodGenericService methodGenericService;
    private EmailValidation emailValidation;

    @Override
    public ResponseEntity<ResponseData<List<Nasabah>>> getAllNasabah() {
        return methodGenericService.extractDataToResponse(nasabahRepository.findAll());
    }

    @Override
    public ResponseEntity<ResponseData<Nasabah>> getNasabahById(Long id) {
        if (!nasabahRepository.existsById(id)){
            return methodGenericService.extractDataToResponseSingle(false,null);
        }
        return methodGenericService.extractDataToResponseSingle(true,nasabahRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<ResponseData<String>> deleteNasabahById(Long id) {
        if (!nasabahRepository.existsById(id)){
            return methodGenericService.extractDataToResponseDelete(false);
        }
        int result = nasabahRepository.deleteNasabahById(id);
        if (result > 0){
            return methodGenericService.extractDataToResponseDelete(true);
        }
        return methodGenericService.extractDataToResponseDelete(false);
    }

    @Override
    public ResponseEntity<ResponseData<Nasabah>> updateNasabahById(Long id, Nasabah nasabah) {

        if (!nasabahRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (nasabah.getPassword() != null && nasabah.getPassword().length() < 6){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Password kurang dari 6 character"),null);
        }
        if (nasabah.getEmail() != null){
            var email = emailValidation.emailValidation(nasabah.getEmail());
            if (email == null){
                return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Value harus berisi email"),null);
            }
        }
        Optional<Nasabah> nasabahById = nasabahRepository.findById(id);
        System.out.println(nasabahById.get().getImgUrl());
        int result = nasabahRepository.updateNasabah(
                nasabah.getUsername() == null ? nasabahById.get().getUsername():nasabah.getUsername(),
                nasabah.getPassword() == null ? nasabahById.get().getPassword():nasabah.getPassword(),
                nasabah.getEmail() == null ? nasabahById.get().getEmail():nasabah.getEmail(),
                Objects.equals(nasabah.getImgUrl(), "") ? nasabahById.get().getImgUrl():nasabah.getImgUrl(),
                nasabah.getAddress() == null ? nasabahById.get().getAddress():nasabah.getAddress(),
                nasabahById.get().getIdNasabah());
        if (result > 0){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data updated");
        }
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data cannot update"),null);
    }

    @Override
    public ResponseEntity<ResponseData<Nasabah>> createNasabah(Nasabah nasabah) {

        List<Nasabah> admins = nasabahRepository.checkUserExists(nasabah.getUsername(), nasabah.getEmail());


        if (!admins.isEmpty()){
            return  methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Username / Email Sudah dipakai"),"Data is Not Saved");
        }

        nasabahRepository.saveAndFlush(nasabah);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Saved");
    }

    @Override
    public ResponseEntity<ResponseData<Nasabah>> login(String username, String email, String password) {
        List<Nasabah> data = nasabahRepository.login(username, email, password);
        if (data.isEmpty()){
            return methodGenericService.extractDataToResponseSingle(false, null);
        }
        return methodGenericService.extractDataToResponseSingle(true, data.get(0));
    }
}
