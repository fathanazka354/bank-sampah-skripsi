package com.skripsi.waste_bank.models;

import com.skripsi.waste_bank.utils.TypeNotification;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "notification_tb")
public class Notification {
    @Id
    @JoinColumn(name = "id_notification")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNotification;

    @JoinColumn(name = "title", nullable = false)
    private String title;

    @JoinColumn(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "type", nullable = false)
    private TypeNotification type;

    @JoinColumn(name = "userId", nullable = false)
    private String userId;

    @CreationTimestamp
    @JoinColumn(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JoinColumn(name = "updated_at")
    private Date updatedAt;
}
