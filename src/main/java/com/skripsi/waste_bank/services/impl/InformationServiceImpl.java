package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.Admin;
import com.skripsi.waste_bank.models.Information;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.repository.InformationRepository;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.services.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired private InformationRepository informationRepository;
    @Autowired private AdminRepository adminRepository;
    @Autowired private NasabahRepository nasabahRepository;

    @Override
    public List<Information> getAllInformation() {
        return informationRepository.getAllInformation();
    }

    @Override
    public List<Information> getAllInformationActive() {
        return informationRepository.getAllInformationActive();
    }

    @Override
    public Information getInformationById(Long id) {
        return informationRepository.findById(id).get();
    }

    @Override
    public String createInformation(Information information, Long idAdmin, Long idNasabah) {
        Optional<Admin> adminOptional = adminRepository.findById(idAdmin);
        Optional<Nasabah> nasabahOptional = nasabahRepository.findById(idNasabah);
        if (nasabahOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        if (adminOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }

        Information information1 = new Information();
        information1.setIdInformation(information.getIdInformation());
        information1.setJudul(information.getJudul());
        information1.setAdmin(adminOptional.get());
        information1.setNasabah(nasabahOptional.get());
        information1.setDeskripsi(information.getDeskripsi());
        information1.setPenerbit(information.getPenerbit());

        informationRepository.saveAndFlush(information1);

        return "Data Added";
    }

    @Override
    public List<Information> createManyInformation(List<Information> informations) {
        return null;
    }

    @Override
    public String updateInformation(Information information) {
        Optional<Information> informationOptional = informationRepository.findById(information.getIdInformation());
        if (informationOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        informationRepository.saveAndFlush(information);
        return "Data Updated";
    }

    @Override
    public String deleteInformation(Long id) {
        Optional<Information> informationOptional = informationRepository.findById(id);
        if (informationOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        int i = informationRepository.deleteInformation(id);
        System.out.println(i);
        if (i == 1){
            return "Data Deleted";
        }
        return "Data Can not Delete";
    }

}
