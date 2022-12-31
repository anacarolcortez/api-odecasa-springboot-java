package com.api.odecasa.services;

import com.api.odecasa.dtos.ApartmentDTO;
import com.api.odecasa.dtos.BuildingDTO;
import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Building;
import com.api.odecasa.repositories.ApartmentRepository;
import com.api.odecasa.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository repository;

    @Autowired
    private ApartmentRepository aptRepository;

    @Transactional
    public BuildingDTO insert(BuildingDTO buildingDTO) {
        Building building = new Building();
        copyDTOtoEntity(buildingDTO, building);
        building = repository.save(building);
        return new BuildingDTO(building, building.getApartments());
    }

    private void copyDTOtoEntity(BuildingDTO buildingDTO, Building building) {
        building.setCNPJ(buildingDTO.getCNPJ());
        building.setAddress(buildingDTO.getAddress());
        building.setZipcode(buildingDTO.getZipcode());
        building.setEmail(buildingDTO.getEmail());
        building.setPhone(buildingDTO.getPhone());

        building.getApartments().clear();
        for (ApartmentDTO apt: buildingDTO.getApartments()){
            Apartment apartment = aptRepository.getReferenceById(apt.getUuid());
            building.getApartments().add(apartment);
        }
    }
}
