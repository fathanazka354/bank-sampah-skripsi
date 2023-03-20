package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.models.JenisPengangkutan;
import com.skripsi.waste_bank.services.JenisPengangkutanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jenis-pengangkutan/")
public class JenisPengangkutanController {
    @Autowired private JenisPengangkutanService jenisPengangkutanService;

    @GetMapping("all")
    public ResponseEntity<List<JenisPengangkutan>> getAllJenisPengangkutan(){
        return ResponseEntity.ok(jenisPengangkutanService.getAllPengangkutan());
    }

    @GetMapping("{id}")
    public ResponseEntity<JenisPengangkutan> getJenisPengangkutanById(@PathVariable("id") Long id){
        return ResponseEntity.ok(jenisPengangkutanService.getJenisPengangkutanById(id));
    }

    @PostMapping("create")
    public ResponseEntity<JenisPengangkutan> createJenisPengangkutan(@RequestBody JenisPengangkutan jenisPengangkutan){
        return ResponseEntity.ok(jenisPengangkutanService.createJenisPengangkutan(jenisPengangkutan));
    }

    @PutMapping("update")
    public ResponseEntity<JenisPengangkutan> updateJenisPengangkutan(@RequestBody JenisPengangkutan jenisPengangkutan){
        return ResponseEntity.ok(jenisPengangkutanService.updateJenisPengangkutan(jenisPengangkutan));
    }
}
