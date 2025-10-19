package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.model.Habitacion;
import com.hospital.gestion_hospitalaria.service.HospitalizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/habitaciones") // <-- URL BASE PARA HABITACIONES
public class HabitacionController {

    @Autowired
    private HospitalizacionService hospitalizacionService;

    @PostMapping
    public ResponseEntity<Habitacion> crearHabitacion(@RequestBody Habitacion habitacion) {
        return new ResponseEntity<>(hospitalizacionService.crearHabitacion(habitacion), HttpStatus.CREATED);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Habitacion>> listarDisponibles() {
        return new ResponseEntity<>(hospitalizacionService.listarHabitacionesDisponibles(), HttpStatus.OK);
    }
}