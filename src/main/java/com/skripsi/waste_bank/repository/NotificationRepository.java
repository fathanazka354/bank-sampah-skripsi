package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.userId = :idUser")
    List<Notification> getAllNotificationsByUserId(String idUser);
}
