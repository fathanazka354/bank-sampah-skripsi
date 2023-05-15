package com.skripsi.waste_bank;

import com.cloudinary.Cloudinary;
import com.skripsi.waste_bank.utils.EmailValidation;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	@Bean
	public EmailValidation emailValidation(){return new EmailValidation();}
}
