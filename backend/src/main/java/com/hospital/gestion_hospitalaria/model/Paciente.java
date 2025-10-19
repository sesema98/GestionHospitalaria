package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data; // @Data incluye @Getter, @Setter, @ToString, etc.
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(length = 100, nullable = false)
    private String apellidos;

    private LocalDate fechaNacimiento;

    @Column(length = 20)
    private String sexo;

    @Column(length = 150)
    private String direccion;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100, unique = true)
    private String correo;

    @Column(length = 20)
    private String estado;
}