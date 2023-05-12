package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.JenisSampah;
import com.skripsi.waste_bank.repository.JenisSampahRepository;
import com.skripsi.waste_bank.services.JenisSampahService;
import com.skripsi.waste_bank.utils.JenisSampahValue;
import com.skripsi.waste_bank.utils.MethodGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class JenisSampahServiceImpl implements JenisSampahService {
    @Autowired private JenisSampahRepository jenisSampahRepository;
    @Autowired private MethodGenericService methodGenericService;
    @Override
    public ResponseEntity<ResponseData<List<JenisSampah>>> getAllJenisSampah() {
        return methodGenericService.extractDataToResponse(jenisSampahRepository.findAll());
    }

    @Override
    public ResponseEntity<ResponseData<JenisSampah>> getJenisSampahById(Long id) {
        return methodGenericService.extractDataToResponseSingle(true,jenisSampahRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<ResponseData<JenisSampah>> createJenisSampah(JenisSampah jenisSampah) {
        jenisSampahRepository.saveAndFlush(jenisSampah);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Saved");
    }

    @Override
    public ResponseEntity<ResponseData<JenisSampah>> updateJenisSampah(Long id, JenisSampah jenisSampah) {
        Optional<JenisSampah> jenisSampahOptional = jenisSampahRepository.findById(id);
        if (jenisSampahOptional.isEmpty()){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data Jenis Sampah is Empty"),"Data is not Updated");
        }
        JenisSampah jenisSampahObj = new JenisSampah();
        jenisSampahObj.setIdJenisSampah(id);
        jenisSampahObj.setNamaJenisSampah(jenisSampah.getNamaJenisSampah());
        jenisSampahObj.setBeratSampah(jenisSampah.getBeratSampah());
        jenisSampahObj.setImgUrl(jenisSampah.getImgUrl());
        jenisSampahRepository.saveAndFlush(jenisSampahObj);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Updated");
    }
}
