package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set; // Usamos Set para evitar especialidades duplicadas

@Data
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico; //

    @Column(length = 100, nullable = false)
    private String nombres; //

    @Column(length = 100, nullable = false)
    private String apellidos; //

    @Column(length = 20, nullable = false, unique = true)
    private String colegiatura; //

    @Column(length = 15)
    private String telefono; //

    @Column(length = 100, unique = true)
    private String correo; //

    @Column(length = 20)
    private String estado; //

    @ManyToMany(fetch = FetchType.EAGER) // Carga las especialidades junto con el médico
    @JoinTable(
            name = "medico_especialidad", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private Set<Especialidad> especialidades;
}