// EspecialidadRepository.java
package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {}

