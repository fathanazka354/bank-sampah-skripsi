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
    @Query("UPDATE Nasabah a SET a.firstName = :firstName," +
            "a.lastName = :lastName, " +
            "a.password = :password, " +
            "a.email = :email, " +
            "a.address = :address, " +
            "a.telephone = :telephone, " +
            "a.isDeleted = :isDeleted WHERE a.idNasabah = :idNasabah")
    int updateNasabah(@Param("firstName")String firstName,
                      @Param("lastName")String lastName,
                      @Param("password") String password,
                      @Param("email") String email,
                      @Param("address")String address,
                      @Param("telephone")String telephone,
                      @Param("isDeleted") Boolean isDeleted,
                      @Param("idNasabah") Long idNasabah
    );
    @Modifying
    @Transactional
    @Query("UPDATE Nasabah a SET a.tabungan = :tabungan " +
            " WHERE a.idNasabah = :idNasabah")
    int updateTabunganNasabah(@Param("idNasabah") Long idNasabah,@Param("tabungan")Double tabungan);
    @Modifying
    @Transactional
    @Query("UPDATE Nasabah a SET a.imgUrl = :imgUrl " +
            " WHERE a.idNasabah = :idNasabah")
    int updateFotoNasabah(@Param("idNasabah") Long idNasabah,@Param("imgUrl")String imgUrl);

    @Query("SELECT a FROM Nasabah a WHERE  a.email = :email")
    List<Nasabah> checkUserExists(@Param("email") String email);

    @Query("SELECT COUNT(a) FROM Nasabah a")
    Integer getUsersTotal();
    @Query("SELECT a FROM Nasabah a WHERE a.isDeleted = false")
    List<Nasabah> getUsersActive();
    @Query("SELECT a FROM Nasabah a WHERE a.isDeleted = true")
    List<Nasabah> getUsersNotActive();

    Optional<Nasabah> findByEmail(String email);

}
