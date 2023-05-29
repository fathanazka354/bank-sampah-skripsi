package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.JenisSampah;
import com.skripsi.waste_bank.services.JenisSampahService;
import com.skripsi.waste_bank.services.SendImageService;
import com.skripsi.waste_bank.utils.Constant;
import com.skripsi.waste_bank.utils.JenisSampahValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jenis-sampah/")
public class JenisSampahController {
    @Autowired private JenisSampahService jenisSampahService;
    @Autowired private SendImageService sendImageService;

    @GetMapping("all")
    public ResponseEntity<ResponseData<List<JenisSampah>>> getAllJenisSampah(){
        return jenisSampahService.getAllJenisSampah();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<JenisSampah>> getJenisSampahById(@PathVariable("id") Long id){
        return jenisSampahService.getJenisSampahById(id);
    }

    @PostMapping("create")
    public ResponseEntity<ResponseData<JenisSampah>> createJenisSampah(@RequestBody JenisSampah jenisSampah){
        return jenisSampahService.createJenisSampah(jenisSampah);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<JenisSampah>> updateJenisSampah(@RequestParam(required = false) JenisSampahValue jenisSampahValue,
                                                                       @PathVariable("id") Long id){
        JenisSampah jenisSampah = new JenisSampah();
        jenisSampah.setNamaJenisSampah(jenisSampahValue);

        return jenisSampahService.updateJenisSampah(id,jenisSampah);
    }
}
