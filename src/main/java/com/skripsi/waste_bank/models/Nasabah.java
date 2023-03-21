package com.skripsi.waste_bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skripsi.waste_bank.utils.Constant;
import jakarta.persistence.*;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nasabah_tb")
public class Nasabah {
    @Id
    @JoinColumn(name = "id_nasabah")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNasabah;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Username can Not blank")
    private String username;

    @Column(name = "email", unique = true)
    @Email(message = "Value Must Email")
    @NotBlank(message = "Email can Not blank")
    private String email;

    @JoinColumn(name = "password")
    @Size(min = 6,message = "Size must equal than 6 character")
    @NotBlank(message = "Password can Not blank")
    private String password;

    @JoinColumn(name = "address")
    @NotBlank(message = "address can Not blank")
    private String address;

    @Column(name = "image_url")
    private String imgUrl = Constant.DEFAULT_URL;

    @Value("false")
    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "nasabah")
    @JsonIgnore
    private List<Information> information;

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
