package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "especialidades")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecialidad;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

    private String descripcion;
}