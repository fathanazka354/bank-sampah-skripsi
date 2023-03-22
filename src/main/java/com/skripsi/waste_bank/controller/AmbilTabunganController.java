package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.AmbilTabungan;
import com.skripsi.waste_bank.services.AmbilTabunganService;
import com.skripsi.waste_bank.services.SendImageService;
import com.skripsi.waste_bank.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ambil-tabungan/")
class AmbilTabunganController {
    @Autowired private AmbilTabunganService ambilTabunganService;
    @Autowired private SendImageService sendImageService;
    @GetMapping("all")
    public ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabungan(){
        return ambilTabunganService.getAllAmbilTabungan();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<AmbilTabungan>> getAmbilTabunganById(@PathVariable("id") Long id){
        return ambilTabunganService.getAmbilTabunganById(id);
    }

    @PostMapping("create/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<AmbilTabungan>> createAmbilTabungan(
                                                                            @RequestParam(required = false) double saldoTaked,
                                                                           @PathVariable("id-admin")Long idAdmin,
                                                                           @PathVariable("id-nasabah")Long idNasabah){
        AmbilTabungan ambilTabungan = new AmbilTabungan();
        ambilTabungan.setSaldoTaked(saldoTaked);
        return ambilTabunganService.createTabungan(ambilTabungan,idNasabah, idAdmin);
    }

    @PutMapping("update/{id}/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<AmbilTabungan>> updateAmbilTabungan(@PathVariable("id") Long id,
                                                                           @PathVariable("id-admin")Long idAdmin,
                                                                           @PathVariable("id-nasabah")Long idNasabah,
                                                                           @RequestParam(required = false) double saldoTaked){
        AmbilTabungan ambilTabungan = new AmbilTabungan();
        ambilTabungan.setSaldoTaked(saldoTaked);
        return ambilTabunganService.updateTabungan(ambilTabungan, id,idAdmin,idNasabah);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteAmbilTabungan(@PathVariable("id") Long idAmbilTabungan){
        return ambilTabunganService.deleteAmbilTabungan(idAmbilTabungan);
    }
}
