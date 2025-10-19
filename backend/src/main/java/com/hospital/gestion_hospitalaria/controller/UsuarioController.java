// UsuarioController.java
package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.model.Usuario;
import com.hospital.gestion_hospitalaria.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private SeguridadService seguridadService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(seguridadService.registrarUsuario(usuario), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuario = seguridadService.findByNombreUsuario(loginRequest.getNombreUsuario());

        if (usuario.isPresent() && usuario.get().getContrasena().equals(loginRequest.getContrasena())) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Credenciales incorrectas
        }
    }
}