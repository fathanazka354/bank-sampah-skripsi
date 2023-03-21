package com.skripsi.waste_bank.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Entity@Table(name = "admin_tb")
public class Admin {
    @Id
    @JoinColumn(name = "idAdmin")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    @NotBlank(message = "Username must be filled")
    @Column(name = "username",unique = true)
    @Size(min = 5,message = "Size char must be more 5")
    private String username;

    @Email(message = "Value must be Email")
    @NotBlank(message = "Email cannot null")
    @Column(name = "email",unique = true)
    private String email;

    @NotBlank(message = "Password must not be null")
    @Size(min = 6,message = "Password must equal than 6 character")
    private String password;

    @Column(name = "image_url")
    private String imgUrl = "";

    @Column(name = "isActive")
    private boolean isActive = true;

    @OneToMany(mappedBy = "nasabah")
    @JsonIgnore
    private List<AmbilTabungan> ambilTabungans;

    @CreationTimestamp
    @JoinColumn(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JoinColumn(name = "updated_at")
    private Date updatedAt;
}
