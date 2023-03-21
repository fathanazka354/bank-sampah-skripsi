package com.skripsi.waste_bank.models;

import com.skripsi.waste_bank.utils.Constant;
import com.skripsi.waste_bank.utils.JenisSampahValue;
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
@Table(name = "jenis_sampah_tb")
public class JenisSampah {
    @Id
    @JoinColumn(name = "id_jenis_sampah")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJenisSampah;

    @JoinColumn(name = "nama_jenis_sampah",nullable = false)
    @Enumerated(EnumType.STRING)
    private JenisSampahValue namaJenisSampah;

    @Column(name = "image_url")
    private String imgUrl = Constant.DEFAULT_URL;

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
