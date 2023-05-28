package com.skripsi.waste_bank.dto;

import com.skripsi.waste_bank.utils.Constant;
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
    @NotBlank(message = "Value Not blank")
    private String firstName;

    @NotBlank(message = "Value Not blank")
    private String lastName;

    @Email(message = "Value Must Email")
    @NotBlank(message = "Value Not blank")
    private String email;

    @Size(min = 6,message = "Size must equal than 6 character")
    @NotBlank(message = "Value Not blank")
    private String password;

    @NotBlank(message = "Value Not blank")
    private String address;

    @NotBlank(message = "Value Not blank")
    private String noTelephone;

    private String imgUrl = Constant.DEFAULT_URL;

    @Value("true")
    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;
}
