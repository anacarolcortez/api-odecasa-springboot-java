package com.api.odecasa.repositories;

import com.api.odecasa.entities.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdvertisingRepository extends JpaRepository<Advertising, UUID> {
}
