package com.skripsi.waste_bank.repository;

import com.skripsi.waste_bank.models.TabungSampahDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TabungSampahDetailRepository extends JpaRepository<TabungSampahDetail, Long> {

}
