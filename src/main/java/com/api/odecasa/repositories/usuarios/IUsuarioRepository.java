package com.api.odecasa.repositories.usuarios;

import com.api.odecasa.models.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {
}
