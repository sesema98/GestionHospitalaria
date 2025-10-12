package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.model.HistoriaClinica;
import com.hospital.gestion_hospitalaria.model.Paciente;
import com.hospital.gestion_hospitalaria.repository.HistoriaClinicaRepository;
import com.hospital.gestion_hospitalaria.repository.PacienteRepository;
import com.hospital.gestion_hospitalaria.service.PacienteService;
import com.hospital.gestion_hospitalaria.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private SeguridadService seguridadService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public Paciente registrarPaciente(Paciente paciente, Long usuarioId) {
        Paciente nuevoPaciente = pacienteRepository.save(paciente);

        HistoriaClinica historia = new HistoriaClinica();
        historia.setPaciente(nuevoPaciente);
        historia.setFechaApertura(LocalDate.now());
        historia.setObservaciones("Historia clínica creada al registrar al paciente.");
        historiaClinicaRepository.save(historia);
        seguridadService.registrarAccion(usuarioId, "Registró al nuevo paciente: " + nuevoPaciente.getNombres() + " " + nuevoPaciente.getApellidos());
        return nuevoPaciente;
    }

    @Override
    public Paciente actualizarPaciente(Long id, Paciente pacienteDetalles) {
        Paciente paciente = obtenerPacientePorId(id);
        paciente.setNombres(pacienteDetalles.getNombres());
        paciente.setApellidos(pacienteDetalles.getApellidos());
        paciente.setDni(pacienteDetalles.getDni());
        paciente.setTelefono(pacienteDetalles.getTelefono());
        paciente.setCorreo(pacienteDetalles.getCorreo());
        paciente.setDireccion(pacienteDetalles.getDireccion());
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    @Override
    public void desactivarPaciente(Long id) {
        Paciente paciente = obtenerPacientePorId(id);
        paciente.setEstado("inactivo");
        pacienteRepository.save(paciente);
    }
}