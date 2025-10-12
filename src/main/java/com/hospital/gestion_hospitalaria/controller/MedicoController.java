package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.model.Especialidad;
import com.hospital.gestion_hospitalaria.model.Medico;
import com.hospital.gestion_hospitalaria.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    // --- Endpoints para Especialidades ---
    @PostMapping("/especialidades")
    public ResponseEntity<Especialidad> crearEspecialidad(@RequestBody Especialidad especialidad) {
        return new ResponseEntity<>(medicoService.crearEspecialidad(especialidad), HttpStatus.CREATED);
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<Especialidad>> listarEspecialidades() {
        return new ResponseEntity<>(medicoService.listarEspecialidades(), HttpStatus.OK);
    }

    // --- Endpoints para Médicos ---
    @PostMapping("/medicos")
    public ResponseEntity<Medico> registrarMedico(@RequestBody Medico medico) {
        return new ResponseEntity<>(medicoService.registrarMedico(medico), HttpStatus.CREATED);
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<Medico>> listarMedicos() {
        return new ResponseEntity<>(medicoService.listarMedicos(), HttpStatus.OK);
    }

    @PostMapping("/medicos/{medicoId}/especialidad/{especialidadId}")
    public ResponseEntity<Medico> asignarEspecialidad(@PathVariable Long medicoId, @PathVariable Long especialidadId) {
        return new ResponseEntity<>(medicoService.asignarEspecialidad(medicoId, especialidadId), HttpStatus.OK);
    }
}