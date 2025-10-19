package com.hospital.gestion_hospitalaria.service;

import com.hospital.gestion_hospitalaria.model.Cita;
import java.util.List;

public interface CitaService {
    Cita agendarCita(Cita cita);
    Cita reprogramarCita(Long id, Cita cita);
    Cita cancelarCita(Long id);
    List<Cita> findByPacienteIdPaciente(Long pacienteId);
    Cita marcarComoAtendida(Long id);
    List<Cita> listarCitas();
}
