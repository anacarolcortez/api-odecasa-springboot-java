package com.api.odecasa.inquilinos.repositories;

import com.api.odecasa.inquilinos.models.InquilinosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IInquilinosRepository extends JpaRepository<InquilinosModel, UUID> {
}
