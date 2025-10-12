package com.hospital.gestion_hospitalaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_recetas")
public class DetalleReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleReceta; //

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    @JsonBackReference // Evita recursión infinita en la serialización JSON
    private RecetaMedica recetaMedica; //

    @Column(length = 100)
    private String medicamento; //

    @Column(length = 100)
    private String dosis; //

    @Column(length = 100)
    private String frecuencia; //

    @Column(length = 50)
    private String duracion; //
}