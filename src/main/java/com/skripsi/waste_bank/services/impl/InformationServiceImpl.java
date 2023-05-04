package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.models.Information;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.repository.InformationRepository;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.services.InformationService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired private InformationRepository informationRepository;
    @Autowired private AdminRepository adminRepository;
    @Autowired private NasabahRepository nasabahRepository;
    @Autowired private MethodGenericService methodGenericService;

    @Override
    public ResponseEntity<ResponseData<List<Information>>> getAllInformation() {
        return methodGenericService.extractDataToResponse(informationRepository.getAllInformation());
    }

    @Override
    public ResponseEntity<ResponseData<List<Information>>> getAllInformationActive() {
        return methodGenericService.extractDataToResponse(informationRepository.getAllInformationActive());
    }

    @Override
    public ResponseEntity<ResponseData<ResponseTotal>> getAllInformationsTotal() {
        var total = informationRepository.getAllInformationTotal();
        return methodGenericService.extractDataToResponseSingle(true,
                ResponseTotal.builder().total(total).section("INFORMATION").build());
    }

    @Override
    public ResponseEntity<ResponseData<Information>> getInformationById(Long id) {
        if (!informationRepository.existsById(id)) {
            return methodGenericService.extractDataToResponseSingle(false,null);
        }
        return methodGenericService.extractDataToResponseSingle(true,informationRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<ResponseData<String>> createInformation(Information information, Long idAdmin) {
        if (!adminRepository.existsById(idAdmin)){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Admin is empty"), "Data is not Saved");
        }

        Information information1 = new Information();
        information1.setIdInformation(information.getIdInformation());
        information1.setJudul(information.getJudul());
        information1.setImgUrl(information.getImgUrl());
        information1.setAdmin(adminRepository.findById(idAdmin).get());
        information1.setDeskripsi(information.getDeskripsi());
        information1.setPenerbit(information.getPenerbit());


        informationRepository.saveAndFlush(information1);

        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""), "Data Saved");
    }

    @Override
    public List<Information> createManyInformation(List<Information> informations) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseData<String>> updateInformation(Long id,Information information) {
        if (!informationRepository.existsById(id)){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Information is empty"), "Data is not Update");
        }
        Information informationObj = new Information();
        Information informationById = informationRepository.findById(id).get();

        String judul = information.getJudul() == null ? informationById.getJudul() : information.getJudul();
        String deskripsi = information.getDeskripsi() == null ? informationById.getDeskripsi() : information.getDeskripsi();
        String penerbit = information.getPenerbit() == null ? informationById.getPenerbit() : information.getPenerbit();
        String imagUrl = information.getImgUrl() == null ? informationById.getImgUrl() : information.getImgUrl();

        informationObj.setIdInformation(id);
        informationObj.setJudul(judul);
        informationObj.setPenerbit(penerbit);
        informationObj.setDeskripsi(deskripsi);
        informationObj.setImgUrl(imagUrl);
        informationObj.setAdmin(informationById.getAdmin());

        informationRepository.saveAndFlush(informationObj);

        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data updated");
    }

    @Override
    public ResponseEntity<ResponseData<String>> deleteInformation(Long id) {
        Optional<Information> informationOptional = informationRepository.findById(id);
        if (informationOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        int i = informationRepository.deleteInformation(id);
        System.out.println(i);
        if (i == 1){
            return methodGenericService.extractDataToResponseDelete(true);
        }
        return methodGenericService.extractDataToResponseDelete(false);
    }

}
