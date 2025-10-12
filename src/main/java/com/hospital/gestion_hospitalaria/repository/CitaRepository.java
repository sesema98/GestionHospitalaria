package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {}
