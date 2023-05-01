package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.dto.ResponseTotal;
import com.skripsi.waste_bank.models.Information;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformationService {
    ResponseEntity<ResponseData<List<Information>>> getAllInformation();
    ResponseEntity<ResponseData<List<Information>>> getAllInformationActive();
    ResponseEntity<ResponseData<ResponseTotal>> getAllInformationsTotal();
    ResponseEntity<ResponseData<Information>> getInformationById(Long id);
    ResponseEntity<ResponseData<String>> createInformation(Information information, Long idAdmin, Long idNasabah);
    List<Information> createManyInformation(List<Information> informations);
    ResponseEntity<ResponseData<String>> updateInformation(Long id,Information information);
    ResponseEntity<ResponseData<String>> deleteInformation(Long id);

}
