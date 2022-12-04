package com.api.odecasa.dtos.inquilinos;

import com.api.odecasa.models.inquilinos.Inquilino;
import lombok.Data;

import java.util.UUID;

@Data
public class ListarInquilinoDTO {

    private UUID id;
    private String apto;
    private String nome;
    private String bio;
    private String telefone;
    private String foto;

    public ListarInquilinoDTO(UUID id, String apto, String nome, String bio, String telefone, String foto) {
        this.id = id;
        this.apto = apto;
        this.nome = nome;
        this.bio = bio;
        this.telefone = telefone;
        this.foto = foto;
    }

    public ListarInquilinoDTO(Inquilino inquilino){
        this(inquilino.getId(), inquilino.getApto(), inquilino.getNome(), inquilino.getBio(), inquilino.getTelefone(), inquilino.getFoto());
    }
}
