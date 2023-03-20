package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.models.Nasabah;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NasabahService {
    List<Nasabah> getAllNasabah();
    Nasabah getNasabahById(Long id);
    int deleteNasabahById(Long id);
    Nasabah updateNasabahById(Nasabah nasabah);
    Nasabah createNasabah(Nasabah nasabah);
}
