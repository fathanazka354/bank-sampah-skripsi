package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface InformationRepository extends JpaRepository<Information,Long> {
    @Query("SELECT i FROM Information i")
    List<Information> getAllInformation();

    @Query("SELECT i FROM Information i WHERE i.isDeleted = false")
    List<Information> getAllInformationActive();

    @Modifying
    @Query("INSERT INTO Information i (i.judul, i.deskripsi, i.penerbit, i.isDeleted, i.createdAt) VALUES (:judul, :deskripsi, :penerbit, false, :createdAt)")
    @Transactional
    int createInformation(@PathVariable("judul") String judul,@PathVariable("deskripsi") String deskripsi,@PathVariable("penerbit") String penerbit,@PathVariable("createdAt") Date createdAt);

    @Modifying
    @Transactional
    @Query("UPDATE Information i SET i.isDeleted = true WHERE i.idInformation = :idInformation")
    int deleteInformation(Long idInformation);
}
