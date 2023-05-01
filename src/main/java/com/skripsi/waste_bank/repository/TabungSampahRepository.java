package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.TabungSampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TabungSampahRepository extends JpaRepository<TabungSampah, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE TabungSampah ts SET ts.isDeleted = true WHERE ts.idTabungSampah =:id")
    int deleteTabungSampah(@Param("id") Long id);

    @Query("SELECT ts FROM TabungSampah ts")
    List<TabungSampah> getAllTabungSampah();

    @Query("SELECT COUNT(ts) FROM TabungSampah ts")
    Integer getAllTabungSampahTotal();

    @Query("SELECT ts FROM TabungSampah ts WHERE ts.idTabungSampah = :idTabungSampah")
    TabungSampah getTabungSampahById(Long idTabungSampah);

}
