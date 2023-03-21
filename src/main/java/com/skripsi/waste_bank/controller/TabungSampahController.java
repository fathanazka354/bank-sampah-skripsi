package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
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
    public ResponseEntity<ResponseData<List<TabungSampah>>> getAllTabungSampah(){
        return tabungSampahService.getAllTabungSampah();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<TabungSampah>> getTabungSampahById(@PathVariable("id") Long id){
        return tabungSampahService.getTabungSampahById(id);
    }

    @GetMapping("nasabah/{id}")
    public ResponseEntity<ResponseData<List<TabungSampah>>> getTabungSampahByIdNasabah(@PathVariable("id") Long id){
        return tabungSampahService.getTabungSampahByIdNasabah(id);
    }

    @PostMapping("create/tabung-sampah-detail/{tabung-sampah-detail}/jenis-pengangkutan/{id-jenis-pengangkutan}/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<TabungSampah>> createTabungSampah(
            @RequestBody TabungSampah tabungSampah,
            @PathVariable("tabung-sampah-detail")Long idTabungSampahDetail,
            @PathVariable("id-jenis-pengangkutan")Long idJenisPengangkutan,
            @PathVariable("id-nasabah")Long idNasabah,
            @PathVariable("id-admin")Long idAdmin
    ){
        return tabungSampahService.createTabungSampah(idTabungSampahDetail, idJenisPengangkutan,idNasabah,idAdmin,tabungSampah);
    }

    @PutMapping("update/{tabung-sampah}/tabung-sampah-detail/{tabung-sampah-detail}/jenis-pengangkutan/{id-jenis-pengangkutan}/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<TabungSampah>> updateTabungSampah(
            @PathVariable("tabung-sampah")Long idTabungSampah,
            @PathVariable("tabung-sampah-detail")Long idTabungSampahDetail,
            @PathVariable("id-jenis-pengangkutan")Long idJenisPengangkutan,
            @PathVariable("id-nasabah")Long idNasabah,
            @PathVariable("id-admin")Long idAdmin,
            @RequestParam(required = false) double totalTabungSampah,
            @RequestParam(required = false) double totalBeratSampah
    ){
        TabungSampah tabungSampah = new TabungSampah();
        tabungSampah.setTotalTabungSampah(totalTabungSampah);
        tabungSampah.setTotalBeratSampah(totalBeratSampah);
        return tabungSampahService.updateTabungSampah(idTabungSampah,idTabungSampahDetail, idJenisPengangkutan,idNasabah,idAdmin, tabungSampah);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteTabungSampah(@PathVariable("id") Long id){
        return tabungSampahService.deleteTabungSampah(id);
    }

}
