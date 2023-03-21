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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tabung_sampah_detail")
    private TabungSampahDetail tabungSampahDetail;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_jenis_pengangkutan")
    private JenisPengangkutan jenisPengangkutan;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_nasabah")
    private Nasabah nasabah;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_admin")
    private Admin admin;

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
