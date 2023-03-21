package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.models.AmbilTabungan;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.repository.AmbilTabunganRepository;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.services.AmbilTabunganService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
class AmbilTabunganServiceImpl implements AmbilTabunganService {
    @Autowired private AmbilTabunganRepository ambilTabunganRepository;
    @Autowired private NasabahRepository nasabahRepository;
    @Autowired private AdminRepository adminRepository;
    @Autowired private MethodGenericService methodGenericService;
    @Override
    public ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabungan() {
        if (ambilTabunganRepository.findAll() == null){
            return methodGenericService.extractDataToResponse(null);
        }
        return methodGenericService.extractDataToResponse(ambilTabunganRepository.findAll());
    }

    @Override
    public ResponseEntity<ResponseData<AmbilTabungan>> getAmbilTabunganById(Long id) {
        Optional<AmbilTabungan> stateAmbilTabungan = ambilTabunganRepository.findById(id);
        if (stateAmbilTabungan.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        return methodGenericService.extractDataToResponseSingle(true,stateAmbilTabungan.get());
    }

    @Override
    public ResponseEntity<ResponseData<AmbilTabungan>> createTabungan(AmbilTabungan ambilTabungan, Long idNasabah, Long idAdmin) {
        Optional<Nasabah> stateNasabah = nasabahRepository.findById(idNasabah);
        if (nasabahRepository.existsById(idNasabah)){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data Nasabah is not exist"),"Data is not Saved");
        }
        if (adminRepository.existsById(idAdmin)){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data Admin is not exist"),"Data is not Saved");
        }

        AmbilTabungan ambilTabunganObj = new AmbilTabungan();

        ambilTabunganObj.setNasabah(stateNasabah.get());
        ambilTabunganObj.setSaldoTaked(ambilTabungan.getSaldoTaked());
        ambilTabunganObj.setNasabah(stateNasabah.get());
        ambilTabunganObj.setAdmin(adminRepository.findById(idAdmin).get());

        ambilTabunganRepository.saveAndFlush(ambilTabunganObj);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Saved");
    }

    @Override
    public ResponseEntity<ResponseData<AmbilTabungan>> updateTabungan(AmbilTabungan ambilTabungan, Long idNasabah) {
        AmbilTabungan ambilTabunganObj = new AmbilTabungan();

        Nasabah nasabah = nasabahRepository.findById(ambilTabungan.getNasabah().getIdNasabah()).get();
        ambilTabunganObj.setNasabah(nasabah);

        ambilTabunganObj.setSaldoTaked(ambilTabungan.getSaldoTaked());

        Admin admin = ambilTabunganRepository.findById(ambilTabungan.getIdAmbilTabungan()).get().getAdmin();
        ambilTabunganObj.setAdmin(admin);

        ambilTabunganRepository.saveAndFlush(ambilTabunganObj);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Updated");
    }

    @Override
    public ResponseEntity<ResponseData<String>> deleteAmbilTabungan(Long idAmbilTabungan) {
        Optional<AmbilTabungan> stateAmbilTabungan = ambilTabunganRepository.findById(idAmbilTabungan);
        if (stateAmbilTabungan.isEmpty()){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Nasabah is not exists"),"Data is not Delete");
        }
        var state = ambilTabunganRepository.deleteAmbilTabungan(idAmbilTabungan);
        if (state == 0){
            return methodGenericService.extractDataToResponseDelete(false);
        }
        return methodGenericService.extractDataToResponseDelete(true);
    }
}
