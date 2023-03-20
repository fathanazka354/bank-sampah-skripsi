package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.models.Information;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformationService {
    List<Information> getAllInformation();
    List<Information> getAllInformationActive();
    Information getInformationById(Long id);
    String createInformation(Information information, Long idAdmin, Long idNasabah);
    List<Information> createManyInformation(List<Information> informations);
    String updateInformation(Information information);
    String deleteInformation(Long id);

}
