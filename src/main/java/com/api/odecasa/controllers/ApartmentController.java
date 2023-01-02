package com.api.odecasa.controllers;

import com.api.odecasa.dtos.ApartmentDTO;
import com.api.odecasa.services.ApartmentService;
import com.api.odecasa.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService service;

    @PostMapping()
    public ResponseEntity<ApartmentDTO> insert(
            @RequestParam(value = "building") UUID buildingId,
            @RequestBody ApartmentDTO apartmentDTO){
        ApartmentDTO apartment = service.insert(buildingId, apartmentDTO);
        return ResponseEntity.ok().body(apartment);
    }
}
