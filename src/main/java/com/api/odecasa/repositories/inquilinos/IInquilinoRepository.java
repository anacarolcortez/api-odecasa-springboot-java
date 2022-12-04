package com.api.odecasa.repositories.inquilinos;

import com.api.odecasa.models.inquilinos.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IInquilinoRepository extends JpaRepository<Inquilino, UUID> {
}
