package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.model.Habitacion;
import com.hospital.gestion_hospitalaria.model.Hospitalizacion;
import com.hospital.gestion_hospitalaria.model.Paciente;
import com.hospital.gestion_hospitalaria.repository.HabitacionRepository;
import com.hospital.gestion_hospitalaria.repository.HospitalizacionRepository;
import com.hospital.gestion_hospitalaria.repository.PacienteRepository;
import com.hospital.gestion_hospitalaria.service.HospitalizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class HospitalizacionServiceImpl implements HospitalizacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private HospitalizacionRepository hospitalizacionRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public List<Habitacion> listarHabitacionesDisponibles() {
        return habitacionRepository.findByEstado("disponible");
    }

    @Override
    @Transactional
    public Hospitalizacion ingresarPaciente(Long pacienteId, Long habitacionId, String diagnosticoIngreso) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        // RF14: Validar que la habitación esté disponible
        if (!"disponible".equalsIgnoreCase(habitacion.getEstado())) {
            throw new RuntimeException("La habitación no está disponible");
        }

        // RF13 y RF14: Ocupar la habitación y registrar la hospitalización
        habitacion.setEstado("ocupada");
        habitacionRepository.save(habitacion);

        Hospitalizacion hospitalizacion = new Hospitalizacion();
        hospitalizacion.setPaciente(paciente);
        hospitalizacion.setHabitacion(habitacion);
        hospitalizacion.setDiagnosticoIngreso(diagnosticoIngreso);
        hospitalizacion.setFechaIngreso(LocalDate.now());
        hospitalizacion.setEstado("activo");

        return hospitalizacionRepository.save(hospitalizacion);
    }

    @Override
    @Transactional
    public Hospitalizacion darDeAlta(Long hospitalizacionId) {
        Hospitalizacion hospitalizacion = hospitalizacionRepository.findById(hospitalizacionId)
                .orElseThrow(() -> new RuntimeException("Registro de hospitalización no encontrado"));

        // RF15: Actualizar el registro con la fecha de alta
        hospitalizacion.setFechaAlta(LocalDate.now());
        hospitalizacion.setEstado("dado de alta");

        // Liberar la habitación
        Habitacion habitacion = hospitalizacion.getHabitacion();
        habitacion.setEstado("disponible");
        habitacionRepository.save(habitacion);

        return hospitalizacionRepository.save(hospitalizacion);
    }
}