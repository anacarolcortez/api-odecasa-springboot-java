package com.api.odecasa.models.anuncios;

import com.api.odecasa.models.tipos.Tipo;
import com.api.odecasa.models.inquilinos.Inquilinos;
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
public class Anuncios implements Serializable {

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
    private Boolean ativo = true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="inquilino_id", referencedColumnName="id")
    private Inquilinos inquilino;

}
