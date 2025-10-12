package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {}

