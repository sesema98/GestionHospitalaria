package com.hospital.gestion_hospitalaria.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura; //

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente; //

    private LocalDate fechaEmision; //

    @Column(precision = 10, scale = 2) // Para manejar valores decimales
    private BigDecimal total; //

    @Column(length = 20)
    private String estado; // (pendiente, pagado)

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleFactura> detalles;
}