package com.hospital.gestion_hospitalaria.service;

import com.hospital.gestion_hospitalaria.model.Habitacion;
import com.hospital.gestion_hospitalaria.model.Hospitalizacion;
import java.util.List;

public interface HospitalizacionService {
    // Métodos para Habitaciones
    Habitacion crearHabitacion(Habitacion habitacion);
    List<Habitacion> listarHabitacionesDisponibles();
    List<Hospitalizacion> findAll();

    // Métodos para Hospitalización
    Hospitalizacion ingresarPaciente(Long pacienteId, Long habitacionId, String diagnosticoIngreso);
    Hospitalizacion darDeAlta(Long hospitalizacionId);
    List<Hospitalizacion> findByPacienteIdPaciente(Long pacienteId);
    List<Habitacion> listarTodasLasHabitaciones();

}