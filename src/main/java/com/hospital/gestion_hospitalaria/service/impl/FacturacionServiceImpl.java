package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.model.DetalleFactura;
import com.hospital.gestion_hospitalaria.model.Factura;
import com.hospital.gestion_hospitalaria.model.Paciente;
import com.hospital.gestion_hospitalaria.repository.FacturaRepository;
import com.hospital.gestion_hospitalaria.repository.PacienteRepository;
import com.hospital.gestion_hospitalaria.service.FacturacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FacturacionServiceImpl implements FacturacionService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @Transactional
    public Factura crearFactura(Long pacienteId, List<DetalleFactura> detalles) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // RF16: Generar facturas
        Factura factura = new Factura();
        factura.setPaciente(paciente);
        factura.setFechaEmision(LocalDate.now());
        factura.setEstado("pendiente"); // RF18: Actualizar estado de la factura

        // Calcular el total sumando los montos de los detalles
        BigDecimal total = detalles.stream()
                .map(DetalleFactura::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        factura.setTotal(total);

        // Asociar los detalles con la factura antes de guardar
        detalles.forEach(detalle -> detalle.setFactura(factura));
        factura.setDetalles(detalles);

        return facturaRepository.save(factura);
    }

    @Override
    public Factura pagarFactura(Long facturaId) {
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        // RF18: Actualizar estado de la factura
        factura.setEstado("pagado");
        return facturaRepository.save(factura);
    }
}