package com.api.odecasa.dtos.usuarios;

import com.api.odecasa.models.perfisacesso.PerfilAcesso;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CadastrarUsuarioDTO {

    @NotBlank
    @Size(max = 20)
    private String usuario;

    @NotBlank
    @Size(max = 100)
    private String senha;

    @NotNull
    private PerfilAcesso perfil;
}
