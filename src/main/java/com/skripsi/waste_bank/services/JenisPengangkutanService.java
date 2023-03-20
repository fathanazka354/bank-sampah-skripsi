package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.models.JenisPengangkutan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JenisPengangkutanService {
    List<JenisPengangkutan> getAllPengangkutan();
    JenisPengangkutan getJenisPengangkutanById(Long id);
    JenisPengangkutan createJenisPengangkutan(JenisPengangkutan jenisPengangkutan);
    JenisPengangkutan updateJenisPengangkutan(JenisPengangkutan jenisPengangkutan);
}
