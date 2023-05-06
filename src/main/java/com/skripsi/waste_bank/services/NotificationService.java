package com.skripsi.waste_bank.services;

import com.skripsi.waste_bank.dto.NotificationRequest;
import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NotificationService {
     ResponseEntity<ResponseData<List<Notification>>> getAllNotifications();
     ResponseEntity<ResponseData<List<Notification>>> getAllNotificationsByIdUser(String idUser);
     ResponseEntity<ResponseData<Notification>> getNotificationById(Long id);
     ResponseEntity<ResponseData<Notification>> createNotification(NotificationRequest notificationRequest);
}
