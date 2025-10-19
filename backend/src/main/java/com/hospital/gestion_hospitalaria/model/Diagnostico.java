package com.hospital.gestion_hospitalaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiagnostico;

    @Lob
    private String descripcion;

    @Column(length = 20)
    private String tipo; // (presuntivo/definitivo)

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false)
    @JsonBackReference // Evita bucles infinitos
    private Consulta consulta;
}
