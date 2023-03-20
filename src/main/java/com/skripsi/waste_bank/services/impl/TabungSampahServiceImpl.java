package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.errors.NullPointerException;
import com.skripsi.waste_bank.models.*;
import com.skripsi.waste_bank.repository.JenisPengambilanRepository;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.repository.TabungSampahDetailRepository;
import com.skripsi.waste_bank.repository.TabungSampahRepository;
import com.skripsi.waste_bank.services.TabungSampahService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TabungSampahServiceImpl implements TabungSampahService {
    private TabungSampahRepository tabungSampahRepository;
    private TabungSampahDetailRepository tabungSampahDetailRepository;
    private NasabahRepository nasabahRepository;
    private JenisPengambilanRepository jenisPengambilanRepository;
    @Override
    public TabungSampah createTabungSampah(TabungSampah tabungSampah, Long idPengangkutan) {
        Optional<JenisPengangkutan> jenisPengangkutanOptional = jenisPengambilanRepository.findById(idPengangkutan);
        if (jenisPengangkutanOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"Value is Empty");
        }

        TabungSampah tabungSampah1 = new TabungSampah();
        tabungSampah1.setIdTabungSampah(tabungSampah.getIdTabungSampah());
        tabungSampah1.setJenisPengangkutan(tabungSampah.getJenisPengangkutan());
        tabungSampah1.setTabungSampahDetail(tabungSampah.getTabungSampahDetail());

        return tabungSampahRepository.saveAndFlush(tabungSampah1);
    }

    @Override
    public TabungSampah updateTabungSampah(TabungSampah tabungSampah) {
        Optional<TabungSampah> tabungSampahOptional = tabungSampahRepository.findById(tabungSampah.getIdTabungSampah());
        if (tabungSampahOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"Value is Empty");
        }
        return tabungSampahRepository.saveAndFlush(tabungSampah);
    }

    @Override
    public String deleteTabungSampah(Long id) {
        Optional<TabungSampah> tabungSampahOptional = tabungSampahRepository.findById(id);
        if (tabungSampahOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"Value is Empty");
        }else {
            tabungSampahRepository.deleteTabungSampah();
            return "Data Delete Successfully";
        }
    }

    @Override
    public List<TabungSampah> getAllTabungSampah() {
        return tabungSampahRepository.getAllTabungSampah();
    }

    @Override
    public TabungSampah getTabungSampahById(Long id) {
        if (tabungSampahRepository.findById(id).isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"Value is not present");
        }
        return tabungSampahRepository.getTabungSampahById(id);
    }

    @Override
    public List<TabungSampah> getTabungSampahByIdNasabah(Long idNasabah) {
        Optional<Nasabah> nasabahOptional = nasabahRepository.findById(idNasabah);
        if (nasabahOptional.isEmpty()){
            throw new NullPointerException(HttpStatus.BAD_REQUEST,"Value is Empty");
        }
        return null;
//        return tabungSampahRepository.getAllTabungSampahByIdNasabah(idNasabah);
    }
}
