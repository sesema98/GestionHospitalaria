package com.hospital.gestion_hospitalaria.repository;

import com.hospital.gestion_hospitalaria.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    // Método para encontrar todas las habitaciones con un estado específico
    List<Habitacion> findByEstado(String estado);
}