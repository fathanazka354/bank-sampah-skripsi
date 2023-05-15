package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.dto.ResponseTotalTabungSampah;
import com.skripsi.waste_bank.models.TabungSampah;
import com.skripsi.waste_bank.services.TabungSampahService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tabung-sampah/")
public class TabungSampahController {
    @Autowired
    private TabungSampahService tabungSampahService;

    @GetMapping("all")
    public ResponseEntity<ResponseData<List<TabungSampah>>> getAllTabungSampah(){
        return tabungSampahService.getAllTabungSampah();
    }

    @GetMapping("total")
    public ResponseEntity<ResponseData<ResponseTotalTabungSampah>> getAllTabungSampahsTotal(){
        return tabungSampahService.getTabungSampahsTotal();
    }

    @GetMapping("total/nasabah/{id}")
    public ResponseEntity<ResponseData<ResponseTotal>> getAllTabungSampahsTotalByIdNasabah(@PathVariable("id") Long idNasabah){
        return tabungSampahService.getTabungSampahsTotalByIdNasabah(idNasabah);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<TabungSampah>> getTabungSampahById(@PathVariable("id") Long id){
        return tabungSampahService.getTabungSampahById(id);
    }

    @PostMapping("create")
    public ResponseEntity<ResponseData<TabungSampah>> createTabungSampah(
            @RequestBody @Valid TabungSampah tabungSampah){
        return tabungSampahService.createTabungSampah(tabungSampah);
    }

    @PutMapping("update/{id-tabung-sampah}")
    public ResponseEntity<ResponseData<TabungSampah>> updateTabungSampah(
            @PathVariable("id-tabung-sampah") Long idTabungSampah,
            @RequestBody @Valid TabungSampah tabungSampah
    ){
        return tabungSampahService.updateTabungSampah(idTabungSampah,tabungSampah);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteTabungSampah(@PathVariable("id") Long id){
        return tabungSampahService.deleteTabungSampah(id);
    }

}
