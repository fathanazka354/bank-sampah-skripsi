package com.skripsi.waste_bank.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "information_tb")
public class Information {
    @Id
    @JoinColumn(name = "id_information")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInformation;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_admin")
    private Admin admin;

    @Column(name = "judul")
    @NotBlank(message = "Judul must be filled")
    private String judul;

    @Column(name = "deskripsi",length = 10000)
    @NotBlank(message = "Deskripsi must be filled")
    private String deskripsi;

    @JoinColumn(name = "penerbit")
    @NotBlank(message = "Penerbit must be filled")
    private String penerbit;

    @Column(name = "image_url",length = 1000)
    private String imgUrl = "";

    @Column(name = "type")
    @NotBlank(message = "Type must be filled")
    private String type;

    @Value("false")
    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;

    @CreationTimestamp
    @JoinColumn(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JoinColumn(name = "updated_at")
    private Date updatedAt;
}
