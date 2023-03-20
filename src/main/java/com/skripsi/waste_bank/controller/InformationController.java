package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.models.Information;
import com.skripsi.waste_bank.services.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/information/")
public class InformationController {
    @Autowired
    InformationService informationService;

    @GetMapping("all")
    public ResponseEntity<List<Information>> getAllInformation(){
        return ResponseEntity.status(HttpStatus.OK).body(informationService.getAllInformation());
    }

    @GetMapping("all-active")
    public ResponseEntity<List<Information>> getAllInformationActive(){
        return ResponseEntity.status(HttpStatus.OK).body(informationService.getAllInformationActive());
    }

    @GetMapping("{id}")
    public ResponseEntity<Information> getInformationById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(informationService.getInformationById(id));
    }

    @PostMapping("create/admin/{id-admin}/nasabah/{id-nasabah}")
    public ResponseEntity<String> createInformation(@RequestBody Information information, @PathVariable("id-admin")Long idAdmin, @PathVariable("id-nasabah")Long idNasabah){
        return ResponseEntity.status(HttpStatus.CREATED).body(informationService.createInformation(information, idAdmin,idNasabah));
    }

    @PutMapping("update")
    public ResponseEntity<String> createInformation(@RequestBody Information information){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(informationService.updateInformation(information));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteInformation(@PathVariable("id")Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(informationService.deleteInformation(id));
    }
}
