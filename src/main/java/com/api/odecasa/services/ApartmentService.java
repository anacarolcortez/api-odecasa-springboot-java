package com.api.odecasa.services;

import com.api.odecasa.dtos.ApartmentDTO;
import com.api.odecasa.dtos.BuildingDTO;
import com.api.odecasa.dtos.TenantDTO;
import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Building;
import com.api.odecasa.entities.Tenant;
import com.api.odecasa.repositories.ApartmentRepository;
import com.api.odecasa.repositories.BuildingRepository;
import com.api.odecasa.repositories.TenantRepository;
import com.api.odecasa.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository repository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Transactional
    public ApartmentDTO insert(UUID buildingID, ApartmentDTO apartmentDTO){
        // precisa validar se ap já está cadastrado nesse prédio
        try{
            Apartment apartment = new Apartment();
            Building building = buildingRepository.getReferenceById(buildingID);
            apartment.setBuilding(building);
            copyDTOtoEntity(apartmentDTO, apartment);
            repository.save(apartment);
            return new ApartmentDTO(apartment, apartment.getBuilding());
        } catch (EntityNotFoundException err){
            throw new ResourceNotFoundException("Building id not found: " + buildingID);
        }
    }

    private void copyDTOtoEntity(ApartmentDTO apartmentDTO, Apartment apartment) {
        if (apartmentDTO.getApt() != null){
            apartment.setApt(apartmentDTO.getApt());
        }

        if (apartmentDTO.getOccupied() != null){
            apartment.setOccupied(apartmentDTO.getOccupied());
        } // se estiver ocupado, precisa também do id do tenant.

    }
}
