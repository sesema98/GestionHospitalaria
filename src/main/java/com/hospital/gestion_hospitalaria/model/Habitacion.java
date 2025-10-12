package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;

    @Column(length = 10, nullable = false, unique = true)
    private String numero;

    @Column(length = 50)
    private String tipo;
    @Column(length = 20, nullable = false)
    private String estado; // (disponible, ocupada, mantenimiento)
}