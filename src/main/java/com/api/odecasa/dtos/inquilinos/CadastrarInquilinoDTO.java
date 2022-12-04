package com.api.odecasa.dtos.inquilinos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CadastrarInquilinoDTO {

    @NotBlank
    @Size(max = 5)
    private String apto;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 150)
    private String bio;

    @NotBlank
    @Size(max = 15)
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")
    private String telefone;

    @NotBlank
    private String foto;

}
