package com.hospital.gestion_hospitalaria.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bitacora")
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBitacora; //

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario; //

    @Column(length = 255)
    private String accion; //

    private LocalDateTime fechaHora; //
}