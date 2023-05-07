package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.NotificationRequest;
import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Notification;
import com.skripsi.waste_bank.repository.AdminRepository;
import com.skripsi.waste_bank.repository.NasabahRepository;
import com.skripsi.waste_bank.repository.NotificationRepository;
import com.skripsi.waste_bank.services.NotificationService;
import com.skripsi.waste_bank.utils.MethodGenericService;
import com.skripsi.waste_bank.utils.TypeNotification;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private MethodGenericService methodGenericService;
    private NotificationRepository notificationRepository;
    private NasabahRepository nasabahRepository;
    private AdminRepository adminRepository;
    @Override
    public ResponseEntity<ResponseData<List<Notification>>> getAllNotifications() {
        return methodGenericService.extractDataToResponse(notificationRepository.findAll());
    }

    @Override
    public ResponseEntity<ResponseData<List<Notification>>> getAllNotificationsByIdUser(String idUser) {
        return methodGenericService.extractDataToResponse(notificationRepository.getAllNotificationsByUserId(idUser));
    }

    @Override
    public ResponseEntity<ResponseData<List<Notification>>> getAllNotificationsByIdUserAndType(String idUser, TypeNotification type) {
        return methodGenericService.extractDataToResponse(notificationRepository.getAllNotificationsByUserIdAndType(idUser, type));
    }

    @Override
    public ResponseEntity<ResponseData<Notification>> getNotificationById(Long id) {
        if (!notificationRepository.existsById(id)){
            return methodGenericService.extractDataToResponseSingle(false,null);
        }
        return methodGenericService.extractDataToResponseSingle(true,notificationRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<ResponseData<Notification>> createNotification(NotificationRequest notificationRequest) {
        if(nasabahRepository.findByEmail(notificationRequest.getUserId()).isEmpty() &&
                adminRepository.findByEmail(notificationRequest.getUserId()).isEmpty()){
            return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList("please check your email"),"Data is not saved");
        };
        Notification notification = Notification.builder()
                .title(notificationRequest.getTitle())
                .description(notificationRequest.getDescription())
                .type(TypeNotification.valueOf(notificationRequest.getType()))
                .userId(notificationRequest.getUserId())
                .build();
        notificationRepository.saveAndFlush(notification);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data saved");
    }
}
