package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NasabahRepository extends JpaRepository<Nasabah, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Nasabah n SET n.isDeleted = true WHERE n.idNasabah = :id")
    int deleteNasabahById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Nasabah a SET a.firstName = :firstName,a.lastName = :lastName, a.password = :password, a.email = :email, a.address = :address, a.imgUrl = :imgUrl, a.tabungan = :tabungan WHERE a.idNasabah = :idNasabah")
    int updateNasabah(@Param("firstName")String firstName,@Param("lastName")String lastName, @Param("password") String password, @Param("email") String email, @Param("imgUrl") String imgUrl, @Param("address")String address,@Param("tabungan")Double tabungan, @Param("idNasabah") Long idNasabah);

    @Query("SELECT a FROM Nasabah a WHERE  a.email = :email")
    List<Nasabah> checkUserExists(@Param("email") String email);

    @Query("SELECT COUNT(a) FROM Nasabah a")
    Integer getUsersTotal();

    Optional<Nasabah> findByEmail(String email);

//    @Query("SELECT n FROM Nasabah n WHERE (n.firstName = :firstName AND n.password = :password) OR (n.email = :email AND n.password = :password)")
//    List<Nasabah> login(@Param("firstName") String firstName, @Param("email") String email, @Param("password") String password);
}
