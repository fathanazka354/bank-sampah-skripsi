//package com.skripsi.waste_bank.controller;
//
//import com.skripsi.waste_bank.dto.ResponseData;
//import com.skripsi.waste_bank.models.JenisPengangkutan;
//import com.skripsi.waste_bank.services.JenisPengangkutanService;
//import com.skripsi.waste_bank.utils.JenisPengangkutanValue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/jenis-pengangkutan/")
//public class JenisPengangkutanController {
//    @Autowired private JenisPengangkutanService jenisPengangkutanService;
//
//    @GetMapping("all")
//    public ResponseEntity<ResponseData<List<JenisPengangkutan>>> getAllJenisPengangkutan(){
//        return jenisPengangkutanService.getAllPengangkutan();
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<ResponseData<JenisPengangkutan>> getJenisPengangkutanById(@PathVariable("id") Long id){
//        return jenisPengangkutanService.getJenisPengangkutanById(id);
//    }
//
//    @PostMapping("create")
//    public ResponseEntity<ResponseData<JenisPengangkutan>> createJenisPengangkutan(@RequestBody JenisPengangkutan jenisPengangkutan){
//        return jenisPengangkutanService.createJenisPengangkutan(jenisPengangkutan);
//    }
//
//    @PutMapping("update/{id}")
//    public ResponseEntity<ResponseData<JenisPengangkutan>> updateJenisPengangkutan(@RequestParam JenisPengangkutanValue jenisPengangkutan,
//                                                                                   @PathVariable("id")Long id){
//
//        return jenisPengangkutanService.updateJenisPengangkutan(id,jenisPengangkutan);
//    }
//}
