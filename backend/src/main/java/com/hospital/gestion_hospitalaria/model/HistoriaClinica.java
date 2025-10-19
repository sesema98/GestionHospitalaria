package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "historias_clinicas")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoria;

    @OneToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "idPaciente", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate fechaApertura;

    private String observaciones;
}