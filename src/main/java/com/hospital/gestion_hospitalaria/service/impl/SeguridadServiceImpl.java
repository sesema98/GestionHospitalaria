package com.hospital.gestion_hospitalaria.service.impl;

import com.hospital.gestion_hospitalaria.model.Bitacora;
import com.hospital.gestion_hospitalaria.model.Usuario;
import com.hospital.gestion_hospitalaria.repository.BitacoraRepository;
import com.hospital.gestion_hospitalaria.repository.UsuarioRepository;
import com.hospital.gestion_hospitalaria.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SeguridadServiceImpl implements SeguridadService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        // RNF2: Cifrado de contraseñas
        // Aquí es donde se cifraría la contraseña antes de guardarla.
        // Ejemplo conceptual con Spring Security:
        // String contrasenaCifrada = passwordEncoder.encode(usuario.getContrasena());
        // usuario.setContrasena(contrasenaCifrada);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void registrarAccion(Long usuarioId, String accion) {
        // RF20 y RNF3: Registrar acciones en la bitácora
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para la bitácora"));

        Bitacora registro = new Bitacora();
        registro.setUsuario(usuario);
        registro.setAccion(accion);
        registro.setFechaHora(LocalDateTime.now());
        bitacoraRepository.save(registro);
    }
}