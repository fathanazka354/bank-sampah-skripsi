package com.skripsi.waste_bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tabung_sampah_detail_tb")
public class TabungSampahDetail {
    @Id
    @JoinColumn(name = "id_tabung_sampah_detail")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTabungSampahDetail;

    @ManyToOne
    @JoinColumn(name = "id_tabung_sampah")
    private TabungSampah tabungSampah;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_jenis_sampah",nullable = false)
    private JenisSampah jenisSampah;

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
