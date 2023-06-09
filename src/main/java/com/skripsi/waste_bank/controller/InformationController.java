package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.models.Information;
import com.skripsi.waste_bank.services.InformationService;
import com.skripsi.waste_bank.services.SendImageService;
import com.skripsi.waste_bank.utils.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/information/")
public class InformationController {
    @Autowired
    InformationService informationService;
    @Autowired
    SendImageService sendImageService;

    @GetMapping("all")
    public ResponseEntity<ResponseData<List<Information>>> getAllInformation(){
        return informationService.getAllInformation();
    }

    @GetMapping("total")
    public ResponseEntity<ResponseData<ResponseTotal>> getAllInformationsTotal(){
        return informationService.getAllInformationsTotal();
    }

    @GetMapping("all-active")
    public ResponseEntity<ResponseData<List<Information>>> getAllInformationActive(){
        return informationService.getAllInformationActive();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<Information>> getInformationById(@PathVariable("id") Long id){
        return informationService.getInformationById(id);
    }

    @PostMapping("create/admin/{id-admin}")
    public ResponseEntity<ResponseData<String>> createInformation(@RequestBody @Valid Information information,
                                                                  @PathVariable("id-admin")Long idAdmin){
        return informationService.createInformation(information, idAdmin);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData<String>> createInformation(@PathVariable("id") Long id,
                                                                  @RequestParam(required = false) String judul,
                                                                  @RequestParam(required = false) String deskripsi,
                                                                  @RequestParam(required = false) String penerbit,
                                                                  @RequestParam(required = false) String file){

        Information information = new Information();

        information.setPenerbit(penerbit);
        information.setDeskripsi(deskripsi);
        information.setJudul(judul);
        information.setImgUrl(file);
        return informationService.updateInformation(id,information);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData<String>> deleteInformation(@PathVariable("id")Long id){
        return informationService.deleteInformation(id);
    }
}
