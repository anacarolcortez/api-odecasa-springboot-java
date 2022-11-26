package com.api.odecasa.inquilinos.dtos;

import com.api.odecasa.inquilinos.models.InquilinosModel;
import lombok.Data;

import java.util.UUID;

@Data
public class ListarInquilinosDTO {

    private UUID id;
    private String apto;
    private String nome;
    private String bio;
    private String telefone;
    private String foto;

    public ListarInquilinosDTO(UUID id, String apto, String nome, String bio, String telefone, String foto) {
        this.id = id;
        this.apto = apto;
        this.nome = nome;
        this.bio = bio;
        this.telefone = telefone;
        this.foto = foto;
    }

    public ListarInquilinosDTO(InquilinosModel inquilino){
        this(inquilino.getId(), inquilino.getApto(), inquilino.getNome(), inquilino.getBio(), inquilino.getTelefone(), inquilino.getFoto());
    }
}
