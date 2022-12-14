package com.api.odecasa.models.usuarios;

import com.api.odecasa.dtos.usuarios.CadastrarUsuarioDTO;
import com.api.odecasa.models.inquilinos.Inquilino;
import com.api.odecasa.models.perfisacesso.PerfilAcesso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Table(name="usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 20, unique = true)
    private String usuario;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PerfilAcesso perfil;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Inquilino inquilino;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now(ZoneId.of("UTC"));

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao = LocalDateTime.now(ZoneId.of("UTC"));

    @Column(nullable = false)
    private Boolean ativo = true;

    public Usuario(CadastrarUsuarioDTO novoUsuario) {
        this.usuario = novoUsuario.getUsuario();
        this.senha = novoUsuario.getSenha();
        this.perfil = novoUsuario.getPerfil();
    }

}
