package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPacienteIdPaciente(Long pacienteId);
    List<Consulta> findByMedicoIdMedico(Long medicoId);
}

