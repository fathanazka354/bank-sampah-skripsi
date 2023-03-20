package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.models.TabungSampah;
import com.skripsi.waste_bank.services.TabungSampahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tabung-sampah/")
public class TabungSampahController {
    @Autowired
    private TabungSampahService tabungSampahService;

    @GetMapping("all")
    public ResponseEntity<List<TabungSampah>> getAllTabungSampah(){
        return ResponseEntity.status(HttpStatus.OK).body(tabungSampahService.getAllTabungSampah());
    }

    @GetMapping("{id}")
    public ResponseEntity<TabungSampah> getTabungSampahById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tabungSampahService.getTabungSampahById(id));
    }

    @GetMapping("nasabah/{id}")
    public ResponseEntity<List<TabungSampah>> getTabungSampahByIdNasabah(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tabungSampahService.getTabungSampahByIdNasabah(id));
    }

    @PostMapping("create/jenis-pengangkutan/{id}")
    public ResponseEntity<TabungSampah> getTabungSampahById(@RequestBody TabungSampah tabungSampah, @PathVariable("id")Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(tabungSampahService.createTabungSampah(tabungSampah, id));
    }

    @PutMapping("update")
    public ResponseEntity<TabungSampah> updateTabungSampah(@RequestBody TabungSampah tabungSampah){
        return ResponseEntity.status(HttpStatus.OK).body(tabungSampahService.updateTabungSampah(tabungSampah));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTabungSampah(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tabungSampahService.deleteTabungSampah(id));
    }

}
