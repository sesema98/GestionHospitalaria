package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.AntecedenteMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // <-- This annotation tells Spring to create a bean for this repository
public interface AntecedenteMedicoRepository extends JpaRepository<AntecedenteMedico, Long> {
    // This file can be empty because JpaRepository already provides the .save() method you need.
}