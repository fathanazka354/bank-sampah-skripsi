package com.skripsi.waste_bank.models;

import com.skripsi.waste_bank.utils.JenisPengangkutanValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "jenis_pengangkutan_tb")
//public class JenisPengangkutan {
//    @Id
//    @JoinColumn(name = "id_jenis_pengangkutan")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idJenisPengangkutan;
//
//    @Enumerated(EnumType.STRING)
//    @JoinColumn(name = "nama_jenis_pengangkutan")
//    private JenisPengangkutanValue jenisPengangkutan = JenisPengangkutanValue.ANTAR;
//
//    @Value("false")
//    @JoinColumn(name = "is_deleted")
//    private boolean isDeleted;
//
//    @CreationTimestamp
//    @JoinColumn(name = "created_at",updatable = false)
//    private Date createdAt;
//    @UpdateTimestamp
//    @JoinColumn(name = "updated_at")
//    private Date updatedAt;
//}
