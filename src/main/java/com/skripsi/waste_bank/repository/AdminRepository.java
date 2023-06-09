package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.firstName = :firstName, a.lastName = :lastName, a.password = :password, a.email = :email, a.imgUrl = :imgUrl, a.isActive = true WHERE a.idAdmin = :idAdmin")
    int updateAdmin(@Param("firstName")String firstName,@Param("lastName")String lastName,@Param("password") String password, @Param("email") String email, @Param("imgUrl") String imgUrl ,@Param("idAdmin") Long idAdmin);

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.isActive = false WHERE a.idAdmin = :idAdmin")
    int deleteAdmin(@Param("idAdmin") Long idAdmin);

    Optional<Admin> findByEmail(String email);


    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    List<Admin> checkUserExists( @Param("email") String email);


    @Query("SELECT a FROM Admin a WHERE a.isActive = true")
    List<Admin> getAllAdminActive();

    @Query("SELECT a FROM Admin a WHERE a.isActive = false")
    List<Admin> getAllAdminNotActive();
}
