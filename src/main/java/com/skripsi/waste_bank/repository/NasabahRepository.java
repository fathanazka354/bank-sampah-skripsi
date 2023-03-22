package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NasabahRepository extends JpaRepository<Nasabah, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Nasabah n SET n.isDeleted = true WHERE n.idNasabah = :id")
    int deleteNasabahById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Nasabah a SET a.username = :username, a.password = :password, a.email = :email, a.address = :address, a.imgUrl = :imgUrl WHERE a.idNasabah = :idNasabah")
    int updateNasabah(@Param("username")String username, @Param("password") String password, @Param("email") String email, @Param("imgUrl") String imgUrl, @Param("address")String address, @Param("idNasabah") Long idNasabah);

    @Query("SELECT a FROM Nasabah a WHERE a.username = :username AND a.email = :email")
    List<Nasabah> checkUserExists(@Param("username") String username, @Param("email") String email);

    @Query("SELECT n FROM Nasabah n WHERE (n.username = :username AND n.password = :password) OR (n.email = :email AND n.password = :password)")
    List<Nasabah> login(@Param("username") String username, @Param("email") String email, @Param("password") String password);
}
