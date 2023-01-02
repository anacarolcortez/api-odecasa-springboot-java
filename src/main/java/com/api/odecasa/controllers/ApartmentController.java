package com.api.odecasa.controllers;

import com.api.odecasa.dtos.ApartmentDTO;
import com.api.odecasa.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService service;

    @PostMapping
    public ResponseEntity<ApartmentDTO> insert(@RequestBody ApartmentDTO apartmentDTO){
        ApartmentDTO apartment = service.insert(apartmentDTO);
        return ResponseEntity.ok().body(apartment);
    }
}
