package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.JenisPengangkutan;
import com.skripsi.waste_bank.repository.JenisPengambilanRepository;
import com.skripsi.waste_bank.services.JenisPengangkutanService;
import com.skripsi.waste_bank.utils.JenisPengangkutanValue;
import com.skripsi.waste_bank.utils.MethodGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class JenisPengangkutanServiceImpl implements JenisPengangkutanService {
    @Autowired private JenisPengambilanRepository jenisPengambilanRepository;
    @Autowired private MethodGenericService methodGenericService;

    @Override
    public ResponseEntity<ResponseData<List<JenisPengangkutan>>> getAllPengangkutan() {
        return methodGenericService.extractDataToResponse(jenisPengambilanRepository.findAll());
    }

    @Override
    public ResponseEntity<ResponseData<JenisPengangkutan>> getJenisPengangkutanById(Long id) {
        Optional<JenisPengangkutan> jenisPengangkutanOptional = jenisPengambilanRepository.findById(id);
        if (jenisPengangkutanOptional.isEmpty()){
            return methodGenericService.extractDataToResponseSingle(false,null);
        }
        return methodGenericService.extractDataToResponseSingle(true,jenisPengambilanRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<ResponseData<JenisPengangkutan>> createJenisPengangkutan(JenisPengangkutan jenisPengangkutan) {
        jenisPengambilanRepository.saveAndFlush(jenisPengangkutan);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Saved");
    }

    @Override
    public ResponseEntity<ResponseData<JenisPengangkutan>> updateJenisPengangkutan(Long idJenisPengangkutan,JenisPengangkutanValue jenisPengangkutanValue) {
        if (!jenisPengambilanRepository.existsById(idJenisPengangkutan)){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"value is not present");
        }
        JenisPengangkutan jenisPengangkutan = new JenisPengangkutan();

        jenisPengangkutan.setIdJenisPengangkutan(idJenisPengangkutan);
        jenisPengangkutan.setJenisPengangkutan(jenisPengangkutanValue);

        jenisPengambilanRepository.saveAndFlush(jenisPengangkutan);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Updated");
    }
}
