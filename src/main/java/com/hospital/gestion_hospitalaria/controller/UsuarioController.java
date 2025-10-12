// UsuarioController.java
package com.hospital.gestion_hospitalaria.controller;

import com.hospital.gestion_hospitalaria.model.Usuario;
import com.hospital.gestion_hospitalaria.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private SeguridadService seguridadService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(seguridadService.registrarUsuario(usuario), HttpStatus.CREATED);
    }
}