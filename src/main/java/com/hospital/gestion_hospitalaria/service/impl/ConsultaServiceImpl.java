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

        Cita cita = citaRepository.findById(consulta.getCita().getIdCita())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado("Atendida");
        citaRepository.save(cita);

        consulta.setPaciente(cita.getPaciente());
        consulta.setMedico(cita.getMedico());
        Consulta consultaGuardada = consultaRepository.save(consulta);


        for (Diagnostico diag : diagnosticos) {
            diag.setConsulta(consultaGuardada);
            diagnosticoRepository.save(diag);
        }

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
}