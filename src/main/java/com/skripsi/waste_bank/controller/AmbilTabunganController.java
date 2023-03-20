package com.skripsi.waste_bank.controller;

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
    public ResponseEntity<List<AmbilTabungan>> getAllAmbilTabungan(){
        return ResponseEntity.ok(ambilTabunganService.getAllAmbilTabungan());
    }

    @GetMapping("{id}")
    public ResponseEntity<AmbilTabungan> getAmbilTabunganById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ambilTabunganService.getAmbilTabunganById(id));
    }

    @PostMapping("create")
    public ResponseEntity<AmbilTabungan> createAmbilTabungan(@RequestBody AmbilTabungan ambilTabungan){
        return ResponseEntity.status(HttpStatus.CREATED).body(ambilTabunganService.createTabungan(ambilTabungan, ambilTabungan.getNasabah().getIdNasabah()));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AmbilTabungan> updateAmbilTabungan(@RequestBody AmbilTabungan ambilTabungan,@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ambilTabunganService.updateTabungan(ambilTabungan, id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAmbilTabungan(@PathVariable("id") Long idAmbilTabungan){
        return ResponseEntity.ok(ambilTabunganService.deleteAmbilTabungan(idAmbilTabungan));
    }
}
