package com.skripsi.waste_bank;

import com.cloudinary.Cloudinary;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class WasteBankApplication {

	public static void main(String[] args)  {
		SpringApplication.run(WasteBankApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary();
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
