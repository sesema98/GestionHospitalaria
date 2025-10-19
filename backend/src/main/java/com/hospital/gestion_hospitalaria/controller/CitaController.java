package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.model.Cita;
import com.hospital.gestion_hospitalaria.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> agendarCita(@RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.agendarCita(cita), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listarCitas() {
        return new ResponseEntity<>(citaService.listarCitas(), HttpStatus.OK);
    }

    @PutMapping("/{id}/reprogramar")
    public ResponseEntity<Cita> reprogramarCita(@PathVariable Long id, @RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.reprogramarCita(id, cita), HttpStatus.OK);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelarCita(@PathVariable Long id) {
        return new ResponseEntity<>(citaService.cancelarCita(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/atender")
    public ResponseEntity<Cita> marcarComoAtendida(@PathVariable Long id) {
        Cita citaActualizada = citaService.marcarComoAtendida(id);
        return new ResponseEntity<>(citaActualizada, HttpStatus.OK);
    }

}