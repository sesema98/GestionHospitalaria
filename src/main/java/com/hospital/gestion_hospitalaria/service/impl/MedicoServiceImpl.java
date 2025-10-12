package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.model.Especialidad;
import com.hospital.gestion_hospitalaria.model.Medico;
import com.hospital.gestion_hospitalaria.repository.EspecialidadRepository;
import com.hospital.gestion_hospitalaria.repository.MedicoRepository;
import com.hospital.gestion_hospitalaria.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public Medico registrarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Especialidad crearEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Override
    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAll();
    }

    @Override
    public Medico asignarEspecialidad(Long medicoId, Long especialidadId) {
        Medico medico = medicoRepository.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        Especialidad especialidad = especialidadRepository.findById(especialidadId).orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        medico.getEspecialidades().add(especialidad);
        return medicoRepository.save(medico);
    }
}