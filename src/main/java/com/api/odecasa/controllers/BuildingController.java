package com.api.odecasa.controllers;

import com.api.odecasa.dtos.BuildingDTO;
import com.api.odecasa.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

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
        return ResponseEntity.created(uri).body(building);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<BuildingDTO> update(@PathVariable UUID id, @RequestBody BuildingDTO buildingDTO){
        BuildingDTO building = service.update(id, buildingDTO);
        return ResponseEntity.ok().body(building);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
