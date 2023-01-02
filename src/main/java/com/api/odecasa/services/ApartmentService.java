package com.api.odecasa.services;

import com.api.odecasa.dtos.ApartmentDTO;
import com.api.odecasa.dtos.TenantDTO;
import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Building;
import com.api.odecasa.entities.Tenant;
import com.api.odecasa.repositories.ApartmentRepository;
import com.api.odecasa.repositories.BuildingRepository;
import com.api.odecasa.repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository repository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Transactional
    public ApartmentDTO insert(@RequestBody ApartmentDTO apartmentDTO){
        Apartment apartment = new Apartment();
        copyDTOtoEntity(apartmentDTO, apartment);
        repository.save(apartment);
        return new ApartmentDTO(apartment);
    }

    private void copyDTOtoEntity(ApartmentDTO apartmentDTO, Apartment apartment) {
        if (apartmentDTO.getApt() != null){
            apartment.setApt(apartmentDTO.getApt());
        }

        if (apartmentDTO.getOccupied() != null){
            apartment.setOccupied(apartmentDTO.getOccupied());
        }

        //Corrigir parse error
        if (apartmentDTO.getBuilding() != null){
            Building building = buildingRepository.getReferenceById(apartmentDTO.getBuilding().getId());
            System.out.println(building);
            apartment.setBuilding(building);
        }

        if (apartmentDTO.getTenants() != null){
            for (TenantDTO t: apartmentDTO.getTenants()){
                Tenant tenant = tenantRepository.getReferenceById(t.getId());
                apartmentDTO.getTenants().add(new TenantDTO(tenant));
            }
        }
    }
}
