package com.api.odecasa.controllers;

import com.api.odecasa.dtos.BuildingDTO;
import com.api.odecasa.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/admin/building")
public class BuildingController {

    @Autowired
    private BuildingService service;

    @PostMapping()
    public ResponseEntity<BuildingDTO> create(@RequestBody BuildingDTO buildingDTO){
        BuildingDTO building = service.insert(buildingDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(building.getUuid())
                .toUri();
        return ResponseEntity.created(uri).build();
    }


}
