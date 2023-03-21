package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.AmbilTabungan;
import com.skripsi.waste_bank.services.AmbilTabunganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ambil-tabungan/")
class AmbilTabunganController {
    @Autowired private AmbilTabunganService ambilTabunganService;
    @GetMapping("all")
    public ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabungan(){
        return ambilTabunganService.getAllAmbilTabungan();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<AmbilTabungan>> getAmbilTabunganById(@PathVariable("id") Long id){
        return ambilTabunganService.getAmbilTabunganById(id);
    }

    @PostMapping("create/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<AmbilTabungan>> createAmbilTabungan(@RequestBody AmbilTabungan ambilTabungan,
                                                                           @PathVariable("id-admin")Long idAdmin,
                                                                           @PathVariable("id-nasabah")Long idNasabah){
        return ambilTabunganService.createTabungan(ambilTabungan,idNasabah, idAdmin);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<AmbilTabungan>> updateAmbilTabungan(@PathVariable("id") Long id,
                                                                           @RequestParam(required = false) double saldoTaked){
        AmbilTabungan ambilTabungan = new AmbilTabungan();
        ambilTabungan.setSaldoTaked(saldoTaked);
        return ambilTabunganService.updateTabungan(ambilTabungan, id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteAmbilTabungan(@PathVariable("id") Long idAmbilTabungan){
        return ambilTabunganService.deleteAmbilTabungan(idAmbilTabungan);
    }
}
