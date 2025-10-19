package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.dto.FacturaDTO;
import com.hospital.gestion_hospitalaria.model.Factura;
import com.hospital.gestion_hospitalaria.service.FacturacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/facturas")
public class FacturacionController {

    @Autowired
    private FacturacionService facturacionService;

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody FacturaDTO facturaDTO) {
        Factura nuevaFactura = facturacionService.crearFactura(
                facturaDTO.getPacienteId(),
                facturaDTO.getDetalles()
        );
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<Factura> pagarFactura(@PathVariable Long id) {
        return new ResponseEntity<>(facturacionService.pagarFactura(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Factura>> listarFacturas() {
        // Necesitamos añadir el método en el servicio
        List<Factura> facturas = facturacionService.findAll();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }
}