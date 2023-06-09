package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.dto.ResponseTotalTabungSampah;
import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.*;
import com.skripsi.waste_bank.repository.*;
import com.skripsi.waste_bank.services.TabungSampahService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TabungSampahServiceImpl implements TabungSampahService {
    private TabungSampahRepository tabungSampahRepository;
    private NasabahRepository nasabahRepository;
    private MethodGenericService methodGenericService;
//    private JenisPengambilanRepository jenisPengambilanRepository;
    @Override
    public ResponseEntity<ResponseData<TabungSampah>> createTabungSampah(TabungSampah tabungSampah) {
        tabungSampahRepository.saveAndFlush(tabungSampah);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Saved");
    }

    @Override
    public ResponseEntity<ResponseData<TabungSampah>> updateTabungSampah(Long idTabungSampah,TabungSampah tabungSampah) {

//        if (!jenisPengambilanRepository.existsById(idTabungSampah)){
//            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("Data Pengangkut is Empty"),"Data is not Updated");
//        }

        tabungSampahRepository.saveAndFlush(tabungSampah);

        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data Updated");
    }

    @Override
    public ResponseEntity<ResponseData<String>> deleteTabungSampah(Long id) {
        Optional<TabungSampah> tabungSampahOptional = tabungSampahRepository.findById(id);
        if (tabungSampahOptional.isPresent()) {
            int result = tabungSampahRepository.deleteTabungSampah(id);
            if (result > 0) {
                return methodGenericService.extractDataToResponseDelete(true);
            }
        }
        return methodGenericService.extractDataToResponseDelete(false);
    }

    @Override
    public ResponseEntity<ResponseData<List<TabungSampah>>> getAllTabungSampah() {
        return methodGenericService.extractDataToResponse(tabungSampahRepository.getAllTabungSampah());
    }

    @Override
    public ResponseEntity<ResponseData<List<TabungSampah>>> getAllTabungSampahByIdNasabah(Long idNasabah) {
        return methodGenericService.extractDataToResponse(tabungSampahRepository.getAllTabungSampahByIdNasabah(idNasabah));
    }

    @Override
    public ResponseEntity<ResponseData<List<TabungSampah>>> getAllTabungSampahByTanggal(Date createdAt, Date updatedAt) {
        return methodGenericService.extractDataToResponse(tabungSampahRepository.getTabungSampahByTanggal(createdAt, updatedAt));
    }

    @Override
    public ResponseEntity<ResponseData<TabungSampah>> getTabungSampahById(Long id) {
        if (!tabungSampahRepository.existsById(id)){
            return methodGenericService.extractDataToResponseSingle(true,null);
        }
        return methodGenericService.extractDataToResponseSingle(true,tabungSampahRepository.getTabungSampahById(id));
    }

    @Override
    public ResponseEntity<ResponseData<List<TabungSampah>>> getTabungSampahByIdNasabah(Long idNasabah) {
        Optional<Nasabah> nasabahOptional = nasabahRepository.findById(idNasabah);
        if (nasabahOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"Value is Empty");
        }
        return null;
    }

    @Override
    public ResponseEntity<ResponseData<ResponseTotalTabungSampah>> getTabungSampahsTotal() {
        var total = tabungSampahRepository.getAllTabungSampahTotal();
        if (total == null){
            total = (double) 0;
        }
        var response = ResponseTotalTabungSampah.builder()
                .total(total)
                .section("TABUNG SAMPAH").build();
        return methodGenericService.extractDataToResponseSingle(true,response);
    }

    @Override
    public ResponseEntity<ResponseData<ResponseTotal>> getTabungSampahsTotalByIdNasabah(Long idNasabah) {
        var total = tabungSampahRepository.getAllTabungSampahTotalByIdNasabah(idNasabah);
        var response = ResponseTotal.builder().total(total).section("TABUNG SAMPAH").build();
        return methodGenericService.extractDataToResponseSingle(true, response);
    }
}
