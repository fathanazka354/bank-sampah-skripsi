package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.services.SendImageService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoController {
    @Autowired
    private SendImageService sendImageService;
    @Autowired private MethodGenericService methodGenericService;
    @PostMapping("/create")
    public ResponseEntity<ResponseData<String>> uploadPhoto(
            @RequestParam(required = false) MultipartFile file
    ){
        var url = sendImageService.uploadImage(file);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(List.of(""),url);
    }

}
