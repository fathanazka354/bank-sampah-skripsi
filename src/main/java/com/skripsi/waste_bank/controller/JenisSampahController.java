package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.models.JenisSampah;
import com.skripsi.waste_bank.services.JenisSampahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jenis-sampah/")
public class JenisSampahController {
    @Autowired private JenisSampahService jenisSampahService;

    @GetMapping("all")
    public ResponseEntity<List<JenisSampah>> getAllJenisSampah(){
        return ResponseEntity.ok(jenisSampahService.getAllJenisSampah());
    }

    @GetMapping("{id}")
    public ResponseEntity<JenisSampah> getJenisSampahById(@PathVariable("id") Long id){
        return ResponseEntity.ok(jenisSampahService.getJenisSampahById(id));
    }

    @PostMapping("create")
    public ResponseEntity<JenisSampah> createJenisSampah(@RequestBody JenisSampah jenisSampah){
        return ResponseEntity.status(HttpStatus.CREATED).body(jenisSampahService.createJenisSampah(jenisSampah));
    }

    @PutMapping("update")
    public ResponseEntity<JenisSampah> updateJenisSampah(@RequestBody JenisSampah jenisSampah){
        return ResponseEntity.status(HttpStatus.OK).body(jenisSampahService.updateJenisSampah(jenisSampah));
    }
}
