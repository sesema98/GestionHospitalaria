package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "hospitalizaciones")
public class Hospitalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHosp;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_habitacion", nullable = false)
    private Habitacion habitacion;

    private LocalDate fechaIngreso;
    private LocalDate fechaAlta;

    @Lob
    private String diagnosticoIngreso;

    @Column(length = 20)
    private String estado;
}