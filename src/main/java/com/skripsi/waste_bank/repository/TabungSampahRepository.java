package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.TabungSampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TabungSampahRepository extends JpaRepository<TabungSampah, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE TabungSampah ts SET ts.isDeleted = true")
    void deleteTabungSampah();

    @Query("SELECT ts FROM TabungSampah ts")
    List<TabungSampah> getAllTabungSampah();

    @Query("SELECT ts FROM TabungSampah ts WHERE ts.idTabungSampah = :idTabungSampah")
    TabungSampah getTabungSampahById(Long idTabungSampah);

//    @Query("SELECT ts FROM TabungSampah ts WHERE ts.idNasabah = :idNasabah")
//    List<TabungSampah> getAllTabungSampahByIdNasabah(Long idNasabah);

}
