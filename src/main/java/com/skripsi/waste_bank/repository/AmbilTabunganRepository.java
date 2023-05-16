package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.AmbilTabungan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface AmbilTabunganRepository extends JpaRepository<AmbilTabungan,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE AmbilTabungan ab SET ab.isDeleted = true WHERE ab.idAmbilTabungan = :idAmbilTabungan")
    int deleteAmbilTabungan(Long idAmbilTabungan);

    @Query("SELECT COUNT(ab) FROM AmbilTabungan ab")
    Integer getAmbilTabunganTotal();

    @Query("SELECT COUNT(ab) FROM AmbilTabungan ab WHERE ab.userId = :idNasabah")
    Integer getAmbilTabunganTotalByIdNasabah(Long idNasabah);

    @Query("SELECT COUNT(ab) FROM AmbilTabungan ab WHERE ab.createdAt = :createdAt OR ab.updatedAt = :updatedAt ")
    List<AmbilTabungan> getAmbilTabunganByTanggal(@Param("createdAt") Date createdAt,
                                            @Param("updatedAt") Date updatedAt);
}
