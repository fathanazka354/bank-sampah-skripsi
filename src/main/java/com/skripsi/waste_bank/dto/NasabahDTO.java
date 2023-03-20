package com.skripsi.waste_bank.dto;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
@Getter
@Setter
public class NasabahDTO {
    @JoinColumn(name = "username")
    @NotBlank(message = "Value Not blank")
    private String username;

    @JoinColumn(name = "email")
    @Email(message = "Value Must Email")
    @NotBlank(message = "Value Not blank")
    private String email;

    @JoinColumn(name = "password")
    @Size(min = 6,message = "Size must equal than 6 character")
    @NotBlank(message = "Value Not blank")
    private String password;

    @JoinColumn(name = "address")
    @NotBlank(message = "Value Not blank")
    private String address;

    @Value("false")
    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;
}
