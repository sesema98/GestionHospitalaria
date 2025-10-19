package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.dto.PacienteRegistroDTO;
import com.hospital.gestion_hospitalaria.model.AntecedenteMedico;
import com.hospital.gestion_hospitalaria.model.HistoriaClinica;
import com.hospital.gestion_hospitalaria.model.Paciente;
import com.hospital.gestion_hospitalaria.repository.AntecedenteMedicoRepository;
import com.hospital.gestion_hospitalaria.repository.HistoriaClinicaRepository;
import com.hospital.gestion_hospitalaria.repository.PacienteRepository;
import com.hospital.gestion_hospitalaria.service.PacienteService;
import com.hospital.gestion_hospitalaria.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;
    @Autowired
    private AntecedenteMedicoRepository antecedenteMedicoRepository; // ✅ Necesario para guardar antecedentes
    @Autowired
    private SeguridadService seguridadService;

    @Override
    @Transactional // Esencial para operaciones de múltiples guardados
    public Paciente registrarPaciente(PacienteRegistroDTO pacienteRegistroDTO, Long usuarioId) {
        // 1. Extraer y guardar el paciente para obtener su ID
        Paciente pacienteParaGuardar = pacienteRegistroDTO.getPaciente();
        Paciente nuevoPaciente = pacienteRepository.save(pacienteParaGuardar);

        // 2. Crear y guardar la HistoriaClinica, vinculada al nuevo paciente
        HistoriaClinica historia = new HistoriaClinica();
        historia.setPaciente(nuevoPaciente);
        historia.setFechaApertura(LocalDate.now());
        historia.setObservaciones("Historia clínica creada al registrar al paciente.");
        HistoriaClinica historiaGuardada = historiaClinicaRepository.save(historia);

        // 3. ✅ Procesar y guardar los Antecedentes Médicos
        List<AntecedenteMedico> antecedentes = pacienteRegistroDTO.getAntecedentes();
        if (antecedentes != null && !antecedentes.isEmpty()) {
            for (AntecedenteMedico antecedente : antecedentes) {
                antecedente.setHistoriaClinica(historiaGuardada); // Vincula cada antecedente a la historia
                antecedenteMedicoRepository.save(antecedente);
            }
        }

        // 4. Registrar la acción en la bitácora
        seguridadService.registrarAccion(usuarioId, "Registró al paciente: " + nuevoPaciente.getNombres());

        return nuevoPaciente;
    }

    // --- El resto de tus métodos (sin cambios) ---

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