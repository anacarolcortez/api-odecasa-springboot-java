package com.api.odecasa.repositories.anuncios;

import com.api.odecasa.models.anuncios.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAnuncioRepository extends JpaRepository<Anuncio, UUID> {
}
