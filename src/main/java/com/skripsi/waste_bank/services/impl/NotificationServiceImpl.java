package com.skripsi.waste_bank.services.impl;

import com.skripsi.waste_bank.dto.NotificationRequest;
import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Notification;
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
    @Override
    public ResponseEntity<ResponseData<List<Notification>>> getAllNotifications() {
        return methodGenericService.extractDataToResponse(notificationRepository.findAll());
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
        Notification notification = Notification.builder()
                .title(notificationRequest.getTitle())
                .description(notificationRequest.getDescription())
                .type(TypeNotification.valueOf(notificationRequest.getType()))
                .build();
        notificationRepository.saveAndFlush(notification);
        return methodGenericService.extractDataToResponseSingleCreateUpdate(Arrays.asList(""),"Data saved");
    }
}
