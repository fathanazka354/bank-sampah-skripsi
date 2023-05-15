package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.configuration.JwtService;
import com.skripsi.waste_bank.dto.*;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.services.NasabahService;
import com.skripsi.waste_bank.utils.EmailValidation;
import com.skripsi.waste_bank.utils.MethodGenericService;
import com.skripsi.waste_bank.utils.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@AllArgsConstructor
public class NasabahServiceImpl implements NasabahService {
    private NasabahRepository nasabahRepository;
    private MethodGenericService methodGenericService;
    private EmailValidation emailValidation;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

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
    public ResponseEntity<ResponseData<ResponseTotal>> getNasabahsTotal() {
        var total = nasabahRepository.getUsersTotal();

        return methodGenericService.extractDataToResponseSingle(true,
                ResponseTotal.builder().total(total).section("NASABAH").build());
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
        int result = nasabahRepository.updateNasabah(
                nasabah.getFirstName() == null ? nasabahById.get().getFirstName():nasabah.getFirstName(),
                nasabah.getLastName() == null ? nasabahById.get().getLastName():nasabah.getLastName(),
                nasabah.getPassword() == null ? nasabahById.get().getPassword(): passwordEncoder.encode(nasabah.getPassword()),
                nasabah.getEmail() == null ? nasabahById.get().getEmail():nasabah.getEmail(),
                Objects.equals(nasabah.getImgUrl(), "") ? nasabahById.get().getImgUrl():nasabah.getImgUrl(),
                nasabah.getAddress() == null ? nasabahById.get().getAddress():nasabah.getAddress(),
                nasabah.getTabungan() == null ? nasabahById.get().getTabungan():nasabah.getTabungan(),
                nasabah.getTelephone() == null ? nasabahById.get().getTelephone():nasabah.getTelephone(),
                nasabahById.get().getIdNasabah());
        if (result > 0){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data updated");
        }
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data cannot update"),null);
    }

    @Override
    public ResponseEntity<ResponseData<Nasabah>> createNasabah(NasabahDTO nasabahDto) {

        List<Nasabah> admins = nasabahRepository.checkUserExists(nasabahDto.getEmail());


        if (!admins.isEmpty()){
            return  methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Username / Email Sudah dipakai"),"Data is Not Saved");
        }

        Nasabah nasabah = Nasabah.builder()
                .firstName(nasabahDto.getFirstName())
                .lastName(nasabahDto.getLastName())
                .address(nasabahDto.getAddress())
                .email(nasabahDto.getEmail())
                .password(passwordEncoder.encode(nasabahDto.getPassword()))
                .role(Role.NASABAH)
                .tabungan(0.0)
                .isDeleted(false)
                .build();

        nasabahRepository.saveAndFlush(nasabah);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Saved");
    }

    @Override
    public ResponseEntity<ResponseData<AuthenticationResponse>> login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        var user = nasabahRepository.findByEmail(email).orElseThrow();
        var jwt =jwtService.generateToken(user);
        var response = AuthenticationResponse.builder()
                .email(email)
                .role(Role.NASABAH)
                .token(jwt)
                .id(user.getIdNasabah())
                .build();
        return methodGenericService.extractDataToResponseSingle(true, response);
    }

    @Override
    public ResponseEntity<ResponseData<Nasabah>> getByEmail(String email) {
        Optional<Nasabah> byEmail = nasabahRepository.findByEmail(email);
        if (byEmail.isEmpty()){
            return methodGenericService.extractDataToResponseSingle(false,null);
        }

        return methodGenericService.extractDataToResponseSingle(true,byEmail.get());
    }
}
