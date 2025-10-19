package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByPacienteIdPaciente(Long pacienteId);
}