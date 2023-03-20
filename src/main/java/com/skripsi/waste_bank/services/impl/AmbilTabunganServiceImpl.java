package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.AmbilTabungan;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.AmbilTabunganRepository;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.services.AmbilTabunganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class AmbilTabunganServiceImpl implements AmbilTabunganService {
    @Autowired private AmbilTabunganRepository ambilTabunganRepository;
    @Autowired private NasabahRepository nasabahRepository;
    @Override
    public List<AmbilTabungan> getAllAmbilTabungan() {
        return ambilTabunganRepository.findAll();
    }

    @Override
    public AmbilTabungan getAmbilTabunganById(Long id) {
        Optional<AmbilTabungan> stateAmbilTabungan = ambilTabunganRepository.findById(id);
        if (stateAmbilTabungan.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        return stateAmbilTabungan.get();
    }

    @Override
    public AmbilTabungan createTabungan(AmbilTabungan ambilTabungan, Long idNasabah) {
        Optional<Nasabah> stateNasabah = nasabahRepository.findById(idNasabah);
        if (stateNasabah.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        AmbilTabungan ambilTabungan1 = new AmbilTabungan();
        ambilTabungan1.setNasabah(stateNasabah.get());
        ambilTabungan1.setSaldoTaked(ambilTabungan.getSaldoTaked());
        return ambilTabunganRepository.saveAndFlush(ambilTabungan1);
    }

    @Override
    public AmbilTabungan updateTabungan(AmbilTabungan ambilTabungan, Long idNasabah) {
        Optional<Nasabah> stateNasabah = nasabahRepository.findById(idNasabah);
        if (stateNasabah.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        AmbilTabungan ambilTabungan1 = new AmbilTabungan();
        ambilTabungan1.setNasabah(stateNasabah.get());
        ambilTabungan1.setSaldoTaked(ambilTabungan.getSaldoTaked());
        return ambilTabunganRepository.saveAndFlush(ambilTabungan1);
    }

    @Override
    public String deleteAmbilTabungan(Long idAmbilTabungan) {
        Optional<AmbilTabungan> stateAmbilTabungan = ambilTabunganRepository.findById(idAmbilTabungan);
        if (stateAmbilTabungan.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        var state = ambilTabunganRepository.deleteAmbilTabungan(idAmbilTabungan);
        if (state == 0){
            return "Failed Deleted";
        }
        return "Data Deleted Successfully";
    }
}
