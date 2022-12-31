package com.api.odecasa.services;

import com.api.odecasa.dtos.ApartmentDTO;
import com.api.odecasa.dtos.BuildingDTO;
import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Building;
import com.api.odecasa.repositories.ApartmentRepository;
import com.api.odecasa.repositories.BuildingRepository;
import com.api.odecasa.services.exceptions.DatabaseException;
import com.api.odecasa.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

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

    @Transactional
    public BuildingDTO update(UUID id, BuildingDTO buildingDTO){
        try{
            Building building = repository.getReferenceById(id);
            copyDTOtoEntity(buildingDTO, building);
            building = repository.save(building);
            return new BuildingDTO(building, building.getApartments());
        } catch (EntityNotFoundException err) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(UUID id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException err){
            throw new ResourceNotFoundException("Id not found" + id);
        } catch (DataIntegrityViolationException err) {
            throw new DatabaseException("Integrity violation");
        }
    }

    @Transactional(readOnly = true)
    public BuildingDTO getById(UUID id){
        Optional<Building> optionalBuilding = repository.findById(id);
        Building building = optionalBuilding.orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
        return new BuildingDTO(building, building.getApartments());
    }

    @Transactional(readOnly = true)
    public Page<BuildingDTO> findAllPaged(PageRequest pageRequest){
        Page<Building> buildings = repository.findAll(pageRequest);
        return buildings.map(b -> new BuildingDTO(b, b.getApartments()));
    }

    private void copyDTOtoEntity(BuildingDTO buildingDTO, Building building) {
        if (buildingDTO.getCNPJ() != null){
            building.setCNPJ(buildingDTO.getCNPJ());
        }

        if (buildingDTO.getAddress() != null){
            building.setAddress(buildingDTO.getAddress());
        }

        if (buildingDTO.getZipcode() != null){
            building.setZipcode(buildingDTO.getZipcode());
        }

        if (buildingDTO.getEmail() != null){
            building.setEmail(buildingDTO.getEmail());
        }

        if (buildingDTO.getPhone() != null){
            building.setPhone(buildingDTO.getPhone());
        }

        building.getApartments().clear();
        for (ApartmentDTO apt: buildingDTO.getApartments()){
            Apartment apartment = aptRepository.getReferenceById(apt.getUuid());
            building.getApartments().add(apartment);
        }
    }
}
