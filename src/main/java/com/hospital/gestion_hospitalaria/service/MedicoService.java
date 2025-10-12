package com.hospital.gestion_hospitalaria.service;

import com.hospital.gestion_hospitalaria.model.Especialidad;
import com.hospital.gestion_hospitalaria.model.Medico;
import java.util.List;

public interface MedicoService {
    Medico registrarMedico(Medico medico);
    List<Medico> listarMedicos();
    Especialidad crearEspecialidad(Especialidad especialidad);
    List<Especialidad> listarEspecialidades();
    Medico asignarEspecialidad(Long medicoId, Long especialidadId);
}
