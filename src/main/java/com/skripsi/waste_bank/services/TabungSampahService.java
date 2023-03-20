package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.models.TabungSampah;
import com.skripsi.waste_bank.models.TabungSampahDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TabungSampahService {
    TabungSampah createTabungSampah(TabungSampah tabungSampah, Long idJenisPengangkutan);
    TabungSampah updateTabungSampah(TabungSampah tabungSampah);
    String deleteTabungSampah(Long id);
    List<TabungSampah> getAllTabungSampah();
    TabungSampah getTabungSampahById(Long id);
    List<TabungSampah> getTabungSampahByIdNasabah(Long idNasabah);
}
