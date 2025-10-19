package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.RecetaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaMedicaRepository extends JpaRepository<RecetaMedica, Long> {}