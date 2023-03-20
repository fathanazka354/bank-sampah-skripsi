package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.JenisSampah;
import com.skripsi.waste_bank.repository.JenisSampahRepository;
import com.skripsi.waste_bank.services.JenisSampahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JenisSampahServiceImpl implements JenisSampahService {
    @Autowired private JenisSampahRepository jenisSampahRepository;
    @Override
    public List<JenisSampah> getAllJenisSampah() {
        return jenisSampahRepository.findAll();
    }

    @Override
    public JenisSampah getJenisSampahById(Long id) {
        Optional<JenisSampah> jenisSampahOptional = jenisSampahRepository.findById(id);
        if (jenisSampahOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        return jenisSampahRepository.findById(id).get();
    }

    @Override
    public JenisSampah createJenisSampah(JenisSampah jenisSampah) {
        return jenisSampahRepository.saveAndFlush(jenisSampah);
    }

    @Override
    public JenisSampah updateJenisSampah(JenisSampah jenisSampah) {
        Optional<JenisSampah> jenisSampahOptional = jenisSampahRepository.findById(jenisSampah.getIdJenisSampah());
        if (jenisSampahOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST, "Value is not present");
        }
        return jenisSampahRepository.save(jenisSampah);
    }
}
