package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {}
