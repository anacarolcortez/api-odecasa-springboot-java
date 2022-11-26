package com.api.odecasa.inquilinos.models;

import com.api.odecasa.inquilinos.dtos.AtualizarInquilinosDTO;
import com.api.odecasa.inquilinos.dtos.CadastrarInquilinosDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="inquilinos")
public class InquilinosModel implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 5)
    private String apto;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 150)
    private String bio;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private Boolean ativo = true;

//    @Column(nullable = false)
    private UUID idCondominio;

//    @Column(nullable = false)
    private UUID idUsuario;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now(ZoneId.of("UTC"));

    public InquilinosModel(CadastrarInquilinosDTO novoInquilino) {
        this.apto = novoInquilino.getApto();
        this.nome = novoInquilino.getNome();
        this.bio = novoInquilino.getBio();
        this.telefone = novoInquilino.getTelefone();
        this.foto = novoInquilino.getFoto();
    }

    public void desativarRegistro() {
        this.ativo = false;
    }

    public void atualizarPeloId(AtualizarInquilinosDTO inquilinoDados) {
        if (inquilinoDados.getApto() != null) {
            this.apto = inquilinoDados.getApto();
        }

        if (inquilinoDados.getNome() != null) {
            this.nome = inquilinoDados.getNome();
        }

        if (inquilinoDados.getBio() != null) {
            this.bio = inquilinoDados.getBio();
        }

        if (inquilinoDados.getTelefone() != null) {
            this.telefone = inquilinoDados.getTelefone();
        }

        if (inquilinoDados.getFoto() != null) {
            this.foto = inquilinoDados.getFoto();
        }
    }
    //Com lombock não precisa incluir boilerplates de getter e setter, ele mesmo faz
}
