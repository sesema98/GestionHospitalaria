package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta; //

    @OneToOne // Una cita solo puede tener una consulta
    @JoinColumn(name = "id_cita", nullable = false, unique = true)
    private Cita cita; //

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico; //

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente; //

    private LocalDate fecha; //
    private LocalTime hora; //

    @Lob
    private String motivoConsulta; //

    @Lob
    private String observaciones; //
}