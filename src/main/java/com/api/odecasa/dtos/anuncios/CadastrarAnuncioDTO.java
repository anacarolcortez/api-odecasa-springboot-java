package com.api.odecasa.dtos.anuncios;

import com.api.odecasa.models.inquilinos.Inquilino;
import com.api.odecasa.models.tipos.Tipo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class CadastrarAnuncioDTO {

    @NotBlank
    @Size(max = 50)
    private String titulo;

    @NotBlank
    @Size(max = 160)
    private String descricao;

    @NotNull
    private Tipo tipo;

    private Inquilino inquilino;
}
