package com.api.odecasa.models.anuncios;

import com.api.odecasa.dtos.anuncios.CadastrarAnuncioDTO;
import com.api.odecasa.models.tipos.Tipo;
import com.api.odecasa.models.inquilinos.Inquilino;
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

@Entity
@Table(name = "anuncios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Anuncio implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 160)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now(ZoneId.of("UTC"));

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao = LocalDateTime.now(ZoneId.of("UTC"));

    @Column(nullable = false)
    private Boolean ativo = true;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="inquilino_id", referencedColumnName="id")
    private Inquilino inquilino;

    public Anuncio(CadastrarAnuncioDTO novoAnuncio) {
        this.titulo = novoAnuncio.getTitulo();
        this.descricao = novoAnuncio.getDescricao();
        this.tipo = novoAnuncio.getTipo();
    }
}
