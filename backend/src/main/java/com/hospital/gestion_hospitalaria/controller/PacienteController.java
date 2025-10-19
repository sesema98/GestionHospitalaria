package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.dto.PacienteRegistroDTO; // ✅ Importa el DTO
import com.hospital.gestion_hospitalaria.model.*;
import com.hospital.gestion_hospitalaria.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:5173")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private CitaService citaService;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private HospitalizacionService hospitalizacionService;
    @Autowired
    private FacturacionService facturacionService;

    @PostMapping
    public ResponseEntity<Paciente> registrar(
            @RequestBody PacienteRegistroDTO pacienteRegistroDTO, // ✅ CORRECCIÓN: Ahora acepta el DTO
            @RequestHeader("X-Usuario-ID") Long usuarioId) {

        Paciente nuevoPaciente = pacienteService.registrarPaciente(pacienteRegistroDTO, usuarioId);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(pacienteService.obtenerPacientePorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable("id") Long id, @RequestBody Paciente paciente) {
        return new ResponseEntity<>(pacienteService.actualizarPaciente(id, paciente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable("id") Long id) {
        pacienteService.desactivarPaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // --- Endpoints Relacionales ---
    @GetMapping("/{id}/citas")
    public ResponseEntity<List<Cita>> getCitasPorPaciente(@PathVariable Long id) {
        return new ResponseEntity<>(citaService.findByPacienteIdPaciente(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/consultas")
    public ResponseEntity<List<Consulta>> getConsultasPorPaciente(@PathVariable Long id) {
        return new ResponseEntity<>(consultaService.findByPacienteIdPaciente(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/hospitalizaciones")
    public ResponseEntity<List<Hospitalizacion>> getHospitalizacionesPorPaciente(@PathVariable Long id) {
        return new ResponseEntity<>(hospitalizacionService.findByPacienteIdPaciente(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/facturas")
    public ResponseEntity<List<Factura>> getFacturasPorPaciente(@PathVariable Long id) {
        return new ResponseEntity<>(facturacionService.findByPacienteIdPaciente(id), HttpStatus.OK);
    }
}