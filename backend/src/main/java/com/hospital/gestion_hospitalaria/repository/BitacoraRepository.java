// BitacoraRepository.java
package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {}