package com.api.odecasa.services;

import com.api.odecasa.dtos.ApartmentDTO;
import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Tenant;
import com.api.odecasa.repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository repository;

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

//        if (apartmentDTO.getBuilding() != null){
//            apartment.se
//        }
    }
}
