package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.Nasabah;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.services.NasabahService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NasabahServiceImpl implements NasabahService {
    private NasabahRepository nasabahRepository;

    @Override
    public List<Nasabah> getAllNasabah() {
        return nasabahRepository.findAll();
    }

    @Override
    public Nasabah getNasabahById(Long id) {
        return nasabahRepository.findById(id).get();
    }

    @Override
    public int deleteNasabahById(Long id) {
        return nasabahRepository.deleteNasabahById(id);
    }

    @Override
    public Nasabah updateNasabahById(Nasabah nasabah) {
        Optional<Nasabah> nasabahById = nasabahRepository.findById(nasabah.getIdNasabah());
        if (nasabahById.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        return nasabahRepository.saveAndFlush(nasabah);
    }

    @Override
    public Nasabah createNasabah(Nasabah nasabah) {
        return nasabahRepository.saveAndFlush(nasabah);
    }
}
