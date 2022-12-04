package com.api.odecasa.dtos.inquilinos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class AtualizarInquilinoDTO {

    @NotNull
    private UUID id;

    @Size(max = 5)
    private String apto;

    @Size(max = 100)
    private String nome;

    @Size(max = 150)
    private String bio;

    @Size(max = 15)
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")
    private String telefone;

    private String foto;

}
