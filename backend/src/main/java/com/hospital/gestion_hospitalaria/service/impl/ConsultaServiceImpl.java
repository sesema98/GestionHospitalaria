// EN: com.hospital.gestionhospitalaria.service.impl.ConsultaServiceImpl.java
package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.model.*;
import com.hospital.gestion_hospitalaria.repository.*;
import com.hospital.gestion_hospitalaria.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private RecetaMedicaRepository recetaMedicaRepository;
    @Autowired
    private CitaRepository citaRepository;


    @Override
    @Transactional
    public Consulta registrarConsultaCompleta(Consulta consulta, List<Diagnostico> diagnosticos, RecetaMedica recetaMedica) {
        // 1. Validar la Cita y actualizar su estado
        Cita cita = citaRepository.findById(consulta.getCita().getIdCita())
                .orElseThrow(() -> new RuntimeException("Error: La cita especificada no existe."));

        if (!"Programada".equalsIgnoreCase(cita.getEstado())) {
            throw new RuntimeException("Error: Solo se puede registrar una consulta para una cita 'Programada'.");
        }

        cita.setEstado("Atendida");
        citaRepository.save(cita);

        // 2. Guardar la entidad Consulta principal
        consulta.setPaciente(cita.getPaciente());
        consulta.setMedico(cita.getMedico());
        Consulta consultaGuardada = consultaRepository.save(consulta);

        // 3. Guardar los Diagnósticos asociados
        if (diagnosticos != null) {
            for (Diagnostico diag : diagnosticos) {
                diag.setConsulta(consultaGuardada);
                diagnosticoRepository.save(diag);
            }
        }

        // 4. Guardar la Receta Médica si existe
        if (recetaMedica != null && recetaMedica.getDetalles() != null && !recetaMedica.getDetalles().isEmpty()) {
            recetaMedica.setConsulta(consultaGuardada);
            recetaMedica.getDetalles().forEach(detalle -> detalle.setRecetaMedica(recetaMedica));
            recetaMedicaRepository.save(recetaMedica);
        }

        return consultaGuardada;
    }

    @Override
    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id).orElseThrow(() -> new RuntimeException("Consulta no encontrada"));
    }

    @Override
    public List<Consulta> findAll() {
        return consultaRepository.findAll();

    }
    @Override
    public List<Consulta> findByPacienteIdPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteIdPaciente(pacienteId);
    }

    @Override
    public List<Consulta> findByMedicoIdMedico(Long medicoId) {
        return consultaRepository.findByMedicoIdMedico(medicoId);
    }
}