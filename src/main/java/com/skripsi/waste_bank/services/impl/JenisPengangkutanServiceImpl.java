package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.JenisPengangkutan;
import com.skripsi.waste_bank.repository.JenisPengambilanRepository;
import com.skripsi.waste_bank.services.JenisPengangkutanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JenisPengangkutanServiceImpl implements JenisPengangkutanService {
    @Autowired private JenisPengambilanRepository jenisPengambilanRepository;
    @Override
    public List<JenisPengangkutan> getAllPengangkutan() {
        return jenisPengambilanRepository.findAll();
    }

    @Override
    public JenisPengangkutan getJenisPengangkutanById(Long id) {
        Optional<JenisPengangkutan> jenisPengangkutanOptional = jenisPengambilanRepository.findById(id);
        if (jenisPengangkutanOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"value is not present");
        }
        return jenisPengambilanRepository.findById(id).get();
    }

    @Override
    public JenisPengangkutan createJenisPengangkutan(JenisPengangkutan jenisPengangkutan) {
        return jenisPengambilanRepository.saveAndFlush(jenisPengangkutan);
    }

    @Override
    public JenisPengangkutan updateJenisPengangkutan(JenisPengangkutan jenisPengangkutan) {
        Optional<JenisPengangkutan> jenisPengangkutanOptional = jenisPengambilanRepository.findById(jenisPengangkutan.getIdJenisPengangkutan());
        if (jenisPengangkutanOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"value is not present");
        }
        return null;
    }
}
