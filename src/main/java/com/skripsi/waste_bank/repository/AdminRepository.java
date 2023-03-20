package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.username = :username, a.password = :password, a.email = :email, a.imgUrl = :imgUrl WHERE a.idAdmin = :idAdmin")
    int updateAdmin(@Param("username")String username,@Param("password") String password, @Param("email") String email, @Param("imgUrl") String imgUrl ,@Param("idAdmin") Long idAdmin);

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.isActive = false WHERE a.idAdmin = :idAdmin")
    int deleteAdmin(@Param("idAdmin") Long idAdmin);
}
