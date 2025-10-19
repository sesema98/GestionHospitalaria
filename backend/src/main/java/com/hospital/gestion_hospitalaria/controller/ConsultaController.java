package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.dto.ConsultaDTO;
import com.hospital.gestion_hospitalaria.model.Consulta;
import com.hospital.gestion_hospitalaria.model.Factura;
import com.hospital.gestion_hospitalaria.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> registrarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        Consulta consultaRegistrada = consultaService.registrarConsultaCompleta(
                consultaDTO.getConsulta(),
                consultaDTO.getDiagnosticos(),
                consultaDTO.getRecetaMedica()
        );
        return new ResponseEntity<>(consultaRegistrada, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obtenerConsultaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(consultaService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarConsultas() {
        // Necesitamos añadir el método en el servicio
        List<Consulta> consultas = consultaService.findAll();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }
}
