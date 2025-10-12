package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.model.Paciente;
import com.hospital.gestion_hospitalaria.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> registrar(@RequestBody Paciente paciente, @RequestHeader("X-Usuario-ID")Long usuarioId) {
        Paciente nuevoPaciente = pacienteService.registrarPaciente(paciente, usuarioId);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable("id") Long id) {
        Paciente paciente = pacienteService.obtenerPacientePorId(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable("id") Long id, @RequestBody Paciente paciente) {
        Paciente pacienteActualizado = pacienteService.actualizarPaciente(id, paciente);
        return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable("id") Long id) {
        pacienteService.desactivarPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}