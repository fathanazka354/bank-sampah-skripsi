package com.skripsi.waste_bank.models;

import jakarta.persistence.*;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ambil_tabungan_tb")
public class AmbilTabungan {
    @Id
    @JoinColumn(name = "id_ambil_tabungan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAmbilTabungan;

    @Value("0")
    @JoinColumn(name = "saldo_taked")
    private double saldoTaked;

    @JoinColumn(name = "date_created", nullable = false)
    private Date dateCreated;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_nasabah")
    private Nasabah nasabah;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_admin")
    private Admin admin;

    @JoinColumn(name = "user_id",nullable = false)
    private Long userId;

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