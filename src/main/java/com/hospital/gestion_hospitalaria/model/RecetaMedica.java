package com.hospital.gestion_hospitalaria.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "recetas_medicas")
public class RecetaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta; //

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta; //

    @Lob
    private String indicaciones; //

    // Una receta tiene muchos detalles (medicamentos)
    @OneToMany(mappedBy = "recetaMedica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Evita recursión infinita en la serialización JSON
    private List<DetalleReceta> detalles;
}