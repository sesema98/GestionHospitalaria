package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "antecedentes_medicos")
public class AntecedenteMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedente;

    @ManyToOne
    @JoinColumn(name = "id_historia", nullable = false)
    private HistoriaClinica historiaClinica;

    @Column(length = 50)
    private String tipo;

    @Lob // Para textos largos
    private String descripcion;
}