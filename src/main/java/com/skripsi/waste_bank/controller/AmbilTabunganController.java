package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.models.AmbilTabungan;
import com.skripsi.waste_bank.services.AmbilTabunganService;
import com.skripsi.waste_bank.services.SendImageService;
import com.skripsi.waste_bank.utils.Constant;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("all/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabunganByIdNasabah(@PathVariable("id-nasabah")Long idNasabah){
        return ambilTabunganService.getAllAmbilTabunganByIdNasabah(idNasabah);
    }
    @GetMapping("date")
    public ResponseEntity<ResponseData<List<AmbilTabungan>>> getAllAmbilTabunganByTanggal( @RequestParam(required = false) String createdAt,
                                                                                           @RequestParam(required = false) String updatedAt) throws ParseException {

        val formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        val createdDate = formatter.parse(createdAt);
        val updatedDate = formatter.parse(updatedAt);
        return ambilTabunganService.getAllAmbilTabunganByTanggal(createdDate,updatedDate);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<AmbilTabungan>> getAmbilTabunganById(@PathVariable("id") Long id){
        return ambilTabunganService.getAmbilTabunganById(id);
    }

    @GetMapping("total")
    public ResponseEntity<ResponseData<ResponseTotal>> getAmbilTabunganByTotal(){
        return ambilTabunganService.getAmbilTabungansTotal();
    }
    @GetMapping("total/nasabah/{id}")
    public ResponseEntity<ResponseData<ResponseTotal>> getAmbilTabunganTotalByIdNasabah(@PathVariable("id")Long idNasabah){
        return ambilTabunganService.getAmbilTabungansTotalByIdNasabah(idNasabah);
    }

    @PostMapping("create/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<AmbilTabungan>> createAmbilTabungan(
                                                                            @RequestParam(required = false) double saldoTaked,
                                                                            @RequestParam(required = false) String dateCreated,
                                                                           @PathVariable("id-admin")Long idAdmin,
                                                                           @PathVariable("id-nasabah")Long idNasabah) throws ParseException {
        AmbilTabungan ambilTabungan = new AmbilTabungan();
        ambilTabungan.setSaldoTaked(saldoTaked);
        val formatter = new SimpleDateFormat("dd-MM-yyyy");
        ambilTabungan.setDateCreated(formatter.parse(dateCreated));
        return ambilTabunganService.createTabungan(ambilTabungan,idNasabah, idAdmin);
    }

    @PutMapping("update/{id}/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<ResponseData<AmbilTabungan>> updateAmbilTabungan(@PathVariable("id") Long id,
                                                                           @PathVariable("id-admin")Long idAdmin,
                                                                           @PathVariable("id-nasabah")Long idNasabah,
                                                                           @RequestParam(required = false) double saldoTaked,
                                                                           @RequestParam(required = false) String dateCreated) throws ParseException {
        AmbilTabungan ambilTabungan = new AmbilTabungan();
        ambilTabungan.setSaldoTaked(saldoTaked);

        val formatter = new SimpleDateFormat("dd-MM-yyyy");
        ambilTabungan.setDateCreated(formatter.parse(dateCreated));
        return ambilTabunganService.updateTabungan(ambilTabungan, id,idAdmin,idNasabah);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteAmbilTabungan(@PathVariable("id") Long idAmbilTabungan){
        return ambilTabunganService.deleteAmbilTabungan(idAmbilTabungan);
    }
}
