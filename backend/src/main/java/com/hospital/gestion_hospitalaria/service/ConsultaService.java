package com.hospital.gestion_hospitalaria.service;

import com.hospital.gestion_hospitalaria.model.Consulta;
import com.hospital.gestion_hospitalaria.model.Diagnostico;
import com.hospital.gestion_hospitalaria.model.RecetaMedica;
import java.util.List;

public interface ConsultaService {
    Consulta registrarConsultaCompleta(Consulta consulta, List<Diagnostico> diagnosticos, RecetaMedica recetaMedica);
    Consulta buscarPorId(Long id);
    List<Consulta> findAll();
    List<Consulta> findByPacienteIdPaciente(Long pacienteId);
    List<Consulta> findByMedicoIdMedico(Long medicoId);
}