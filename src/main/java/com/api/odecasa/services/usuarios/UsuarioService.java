package com.api.odecasa.services.usuarios;

import com.api.odecasa.dtos.usuarios.CadastrarUsuarioDTO;
import com.api.odecasa.models.usuarios.Usuario;
import com.api.odecasa.repositories.usuarios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(CadastrarUsuarioDTO novoUsuario){
        Usuario usuario = new Usuario(novoUsuario);
        return usuarioRepository.save(usuario);
    }
}
