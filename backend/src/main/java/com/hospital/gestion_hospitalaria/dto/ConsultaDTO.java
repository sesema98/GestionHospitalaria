package com.hospital.gestion_hospitalaria.dto;

import com.hospital.gestion_hospitalaria.model.Consulta;
import com.hospital.gestion_hospitalaria.model.Diagnostico;
import com.hospital.gestion_hospitalaria.model.RecetaMedica;
import lombok.Data;
import java.util.List;

@Data
public class ConsultaDTO {
    private Consulta consulta;
    private List<Diagnostico> diagnosticos;
    private RecetaMedica recetaMedica;
}