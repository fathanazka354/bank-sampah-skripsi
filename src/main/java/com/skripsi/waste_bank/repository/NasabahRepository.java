package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NasabahRepository extends JpaRepository<Nasabah, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Nasabah n SET n.isDeleted = true")
    int deleteNasabahById(Long id);
}
