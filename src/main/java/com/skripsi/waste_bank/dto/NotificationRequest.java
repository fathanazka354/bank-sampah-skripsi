package com.skripsi.waste_bank.dto;

import com.skripsi.waste_bank.utils.TypeNotification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    @NotBlank(message = "title field can not blank")
    private String title;

    @NotBlank(message = "description field can not blank")
    private String description;

    @NotBlank(message = "type field can not blank")
    @Enumerated(EnumType.STRING)
    private String type;
}
