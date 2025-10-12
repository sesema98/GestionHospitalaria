package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario; //

    @Column(length = 50, nullable = false, unique = true)
    private String nombreUsuario; //

    @Column(nullable = false)
    private String contrasena; //

    @Column(length = 30)
    private String rol; // (admin, medico, recepcionista, enfermera)
}