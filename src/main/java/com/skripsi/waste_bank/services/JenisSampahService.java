package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.models.JenisSampah;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JenisSampahService{
    List<JenisSampah> getAllJenisSampah();
    JenisSampah getJenisSampahById(Long id);
    JenisSampah createJenisSampah(JenisSampah jenisSampah);
    JenisSampah updateJenisSampah(JenisSampah jenisSampah);
}
