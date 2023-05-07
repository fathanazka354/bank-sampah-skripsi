package com.skripsi.waste_bank.controller;

import com.skripsi.waste_bank.dto.NotificationRequest;
import com.skripsi.waste_bank.dto.ResponseData;
import com.skripsi.waste_bank.models.Notification;
import com.skripsi.waste_bank.services.NotificationService;
import com.skripsi.waste_bank.utils.TypeNotification;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification/")
@AllArgsConstructor
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping("all")
    public ResponseEntity<ResponseData<List<Notification>>> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @PostMapping("user")
    public ResponseEntity<ResponseData<List<Notification>>> getAllNotificationsByIdUser(@RequestParam("email")String email){
        return notificationService.getAllNotificationsByIdUser(email);
    }
    @PostMapping("user-type")
    public ResponseEntity<ResponseData<List<Notification>>> getAllNotificationsByIdUserAndType(@RequestParam("email")String email,
                                                                                               @RequestParam("type")String type){
        return notificationService.getAllNotificationsByIdUserAndType(email, TypeNotification.valueOf(type));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Notification>> getNotificationById(@PathVariable("id") Long id){
        return notificationService.getNotificationById(id);
    }
    @PostMapping("create")
    public ResponseEntity<ResponseData<Notification>> createNotification(@RequestBody @Valid NotificationRequest notificationRequest){
        return notificationService.createNotification(notificationRequest);
    }
}
