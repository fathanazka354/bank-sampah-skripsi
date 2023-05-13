package com.skripsi.waste_bank.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tabung_sampah_tb")
public class TabungSampah {
    @Id
    @JoinColumn(name = "id_tabung_sampah")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTabungSampah;

    @JoinColumn(name = "total_tabung_sampah")
    private double totalTabungSampah;

    @JoinColumn(name = "total_berat_sampah")
    private double totalBeratSampah;

    @JoinColumn(name = "description")
    private String description;

    @JoinColumn(name = "img_url")
    private String imgUrl;

    @JoinColumn(name = "no_telephone", nullable = false)
    private String noTelephone;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TabungSampahDetail> tabungSampahDetail;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_jenis_pengangkutan")
    private JenisPengangkutan jenisPengangkutan;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_nasabah")
    private Nasabah nasabah;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_admin")
    private Admin admin;

    @JoinColumn(name = "nasabah",nullable = false)
    private Long nasabahId;

    @JoinColumn(name = "admin",nullable = false)
    private Long adminId;

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