package com.hospital.gestion_hospitalaria.dto;

import com.hospital.gestion_hospitalaria.model.AntecedenteMedico;
import com.hospital.gestion_hospitalaria.model.Paciente;
import lombok.Data;
import java.util.List;

@Data
public class PacienteRegistroDTO {
    private Paciente paciente;
    private List<AntecedenteMedico> antecedentes;
}