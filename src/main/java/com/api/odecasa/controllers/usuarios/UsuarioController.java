package com.api.odecasa.controllers.usuarios;

import com.api.odecasa.dtos.usuarios.CadastrarUsuarioDTO;
import com.api.odecasa.models.usuarios.Usuario;
import com.api.odecasa.services.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO novoUsuario){
        Usuario usuario = usuarioService.save(novoUsuario);
        return ResponseEntity.status(201).body(usuario);
    }
}
