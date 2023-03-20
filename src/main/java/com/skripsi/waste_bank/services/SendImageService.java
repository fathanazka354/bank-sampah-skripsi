package com.skripsi.waste_bank.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public
interface SendImageService {
    public String uploadImage(MultipartFile file);
}
