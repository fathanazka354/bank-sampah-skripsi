package com.skripsi.waste_bank.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public
interface SendImageService {
    public String uploadImage(MultipartFile file);
    public String uploadFile(MultipartFile multipartFile) throws IOException;
}
