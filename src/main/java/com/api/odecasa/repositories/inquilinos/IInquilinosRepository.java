package com.api.odecasa.repositories.inquilinos;

import com.api.odecasa.models.inquilinos.Inquilinos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IInquilinosRepository extends JpaRepository<Inquilinos, UUID> {
}
