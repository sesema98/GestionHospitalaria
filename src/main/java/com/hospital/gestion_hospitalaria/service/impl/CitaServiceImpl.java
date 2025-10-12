package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.model.Cita;
import com.hospital.gestion_hospitalaria.model.Medico;
import com.hospital.gestion_hospitalaria.model.Paciente;
import com.hospital.gestion_hospitalaria.repository.CitaRepository;
import com.hospital.gestion_hospitalaria.repository.MedicoRepository;
import com.hospital.gestion_hospitalaria.repository.PacienteRepository;
import com.hospital.gestion_hospitalaria.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public Cita agendarCita(Cita cita) {
        Paciente paciente = pacienteRepository.findById(cita.getPaciente().getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(cita.getMedico().getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setEstado("Programada");
        return citaRepository.save(cita);
    }

    @Override
    public Cita reprogramarCita(Long id, Cita citaDetalles) {
        Cita cita = citaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setFecha(citaDetalles.getFecha());
        cita.setHora(citaDetalles.getHora());
        return citaRepository.save(cita);
    }

    @Override
    public Cita cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado("Cancelada");
        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }
}