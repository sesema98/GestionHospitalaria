package com.hospital.gestion_hospitalaria.service;

import com.hospital.gestion_hospitalaria.dto.PacienteRegistroDTO;
import com.hospital.gestion_hospitalaria.model.Paciente;
import java.util.List;

public interface PacienteService {

    Paciente registrarPaciente(PacienteRegistroDTO pacienteRegistroDTO, Long usuarioId);

    Paciente actualizarPaciente(Long id, Paciente paciente);
    List<Paciente> listarPacientes();
    Paciente obtenerPacientePorId(Long id);
    void desactivarPaciente(Long id);
}