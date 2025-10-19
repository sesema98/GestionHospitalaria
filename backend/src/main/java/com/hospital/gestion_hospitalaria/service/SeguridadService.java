package com.hospital.gestion_hospitalaria.service;

import com.hospital.gestion_hospitalaria.model.Usuario;
import java.util.Optional;

public interface SeguridadService {
    Usuario registrarUsuario(Usuario usuario);
    void registrarAccion(Long usuarioId, String accion);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}