package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.model.Habitacion;
import com.hospital.gestion_hospitalaria.model.Hospitalizacion;
import com.hospital.gestion_hospitalaria.service.HospitalizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HospitalizacionController {

    @Autowired
    private HospitalizacionService hospitalizacionService;

    // --- Endpoints para Habitaciones ---
    @PostMapping("/habitaciones")
    public ResponseEntity<Habitacion> crearHabitacion(@RequestBody Habitacion habitacion) {
        return new ResponseEntity<>(hospitalizacionService.crearHabitacion(habitacion), HttpStatus.CREATED);
    }

    @GetMapping("/habitaciones/disponibles")
    public ResponseEntity<List<Habitacion>> listarDisponibles() {
        return new ResponseEntity<>(hospitalizacionService.listarHabitacionesDisponibles(), HttpStatus.OK);
    }

    // --- Endpoints para Hospitalización ---
    @PostMapping("/hospitalizaciones/ingresar")
    public ResponseEntity<Hospitalizacion> ingresarPaciente(@RequestBody Map<String, Object> payload) {
        Long pacienteId = Long.parseLong(payload.get("pacienteId").toString());
        Long habitacionId = Long.parseLong(payload.get("habitacionId").toString());
        String diagnostico = payload.get("diagnosticoIngreso").toString();

        Hospitalizacion hospitalizacion = hospitalizacionService.ingresarPaciente(pacienteId, habitacionId, diagnostico);
        return new ResponseEntity<>(hospitalizacion, HttpStatus.CREATED);
    }

    @PutMapping("/hospitalizaciones/{id}/alta")
    public ResponseEntity<Hospitalizacion> darDeAlta(@PathVariable Long id) {
        return new ResponseEntity<>(hospitalizacionService.darDeAlta(id), HttpStatus.OK);
    }
}