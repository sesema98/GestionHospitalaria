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
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/hospitalizaciones") // <-- CAMBIO CLAVE AQUÍ
public class HospitalizacionController {

    @Autowired
    private HospitalizacionService hospitalizacionService;

    @PostMapping("/ingresar")
    public ResponseEntity<Hospitalizacion> ingresarPaciente(@RequestBody Map<String, Object> payload) {
        Long pacienteId = Long.parseLong(payload.get("pacienteId").toString());
        Long habitacionId = Long.parseLong(payload.get("habitacionId").toString());
        String diagnostico = payload.get("diagnosticoIngreso").toString();

        Hospitalizacion hospitalizacion = hospitalizacionService.ingresarPaciente(pacienteId, habitacionId, diagnostico);
        return new ResponseEntity<>(hospitalizacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/alta")
    public ResponseEntity<Hospitalizacion> darDeAlta(@PathVariable Long id) {
        return new ResponseEntity<>(hospitalizacionService.darDeAlta(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hospitalizacion>> listarHospitalizaciones() {
        List<Hospitalizacion> lista = hospitalizacionService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}