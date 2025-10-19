package com.hospital.gestion_hospitalaria.service;

import com.hospital.gestion_hospitalaria.model.DetalleFactura;
import com.hospital.gestion_hospitalaria.model.Factura;
import java.util.List;

public interface FacturacionService {
    Factura crearFactura(Long pacienteId, List<DetalleFactura> detalles);
    Factura pagarFactura(Long facturaId);
    List<Factura> findAll();
    List<Factura> findByPacienteIdPaciente(Long pacienteId);
}