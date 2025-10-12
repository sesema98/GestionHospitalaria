package com.hospital.gestion_hospitalaria.dto;

import com.hospital.gestion_hospitalaria.model.DetalleFactura;
import lombok.Data;
import java.util.List;

@Data
public class FacturaDTO {
    private Long pacienteId;
    private List<DetalleFactura> detalles;
}