package com.api.odecasa.models.inquilinos;

import com.api.odecasa.dtos.inquilinos.AtualizarInquilinoDTO;
import com.api.odecasa.dtos.inquilinos.CadastrarInquilinoDTO;
import com.api.odecasa.dtos.inquilinos.ListarInquilinoDTO;
import com.api.odecasa.models.anuncios.Anuncio;
import com.api.odecasa.models.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="inquilinos")
public class Inquilino implements Serializable {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_inquilino",
            joinColumns =
                    { @JoinColumn(name = "inquilino_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "usuario_id", referencedColumnName = "id") })
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now(ZoneId.of("UTC"));

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao = LocalDateTime.now(ZoneId.of("UTC"));

    @JsonIgnore
    @OneToMany(mappedBy = "inquilino")
    private Set<Anuncio> anuncios = new HashSet<>();

    public Inquilino(CadastrarInquilinoDTO novoInquilino) {
        this.apto = novoInquilino.getApto();
        this.nome = novoInquilino.getNome();
        this.bio = novoInquilino.getBio();
        this.telefone = novoInquilino.getTelefone();
        this.foto = novoInquilino.getFoto();
    }

    public Inquilino(ListarInquilinoDTO inquilino) {
        this.id = inquilino.getId();
        this.apto = inquilino.getApto();
        this.nome = inquilino.getNome();
        this.bio = inquilino.getBio();
        this.telefone = inquilino.getTelefone();
        this.foto = inquilino.getFoto();
    }

    public void desativarRegistro() {
        this.ativo = false;
    }

    public void atualizarPeloId(AtualizarInquilinoDTO inquilinoDados) {
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

        this.dataAtualizacao =  LocalDateTime.now(ZoneId.of("UTC"));
    }
}
