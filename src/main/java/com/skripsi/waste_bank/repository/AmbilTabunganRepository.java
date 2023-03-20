package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.AmbilTabungan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AmbilTabunganRepository extends JpaRepository<AmbilTabungan,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE AmbilTabungan ab SET ab.isDeleted = true WHERE ab.idAmbilTabungan = :idAmbilTabungan")
    int deleteAmbilTabungan(Long idAmbilTabungan);
}
