package com.hospital.gestion_hospitalaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detalle_facturas")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleFactura; //

    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    @JsonBackReference
    private Factura factura; //

    @Column(length = 255)
    private String concepto; // (consulta, medicamento, procedimiento)

    @Column(precision = 10, scale = 2)
    private BigDecimal monto; //
}