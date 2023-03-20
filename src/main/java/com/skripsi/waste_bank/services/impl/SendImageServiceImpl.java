package com.skripsi.waste_bank.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.skripsi.waste_bank.services.SendImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class SendImageServiceImpl implements SendImageService {

    @Autowired @Value("${CLOUDINARY_URL}")
    Cloudinary cloudinary;
    @Override
    public String uploadImage(MultipartFile file) {
        try {
            return image(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String image(MultipartFile file) throws IOException {

        byte[] bit = file.getBytes();
        Files.write(Path.of("src/main/java/com/skripsi/waste_bank/media/note.png"),bit);

        String filename = String.valueOf(UUID.randomUUID());
        cloudinary.uploader().upload(new File("src/main/java/com/skripsi/waste_bank/media/note.png"),
                ObjectUtils.asMap("public_id", filename));

        String url = cloudinary.url().imageTag(filename);
        url = url.replaceAll("<img src='","");
        url = url.replaceAll("'\\/>","");
        return url;
    }
}
