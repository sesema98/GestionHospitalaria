package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Hospitalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalizacionRepository extends JpaRepository<Hospitalizacion, Long> {}