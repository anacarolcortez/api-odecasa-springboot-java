package com.api.odecasa.controllers;

import com.api.odecasa.dtos.BuildingDTO;
import com.api.odecasa.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService service;

    @PostMapping()
    public ResponseEntity<BuildingDTO> create(@RequestBody BuildingDTO buildingDTO){
        BuildingDTO building = service.insert(buildingDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(building.getId())
                .toUri();
        return ResponseEntity.created(uri).body(building);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<BuildingDTO> update(@PathVariable UUID id, @RequestBody BuildingDTO buildingDTO){
        BuildingDTO building = service.update(id, buildingDTO);
        return ResponseEntity.ok().body(building);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BuildingDTO> findById(@PathVariable UUID id){
        BuildingDTO building = service.getById(id);
        return ResponseEntity.ok().body(building);
    }

    @GetMapping
    public ResponseEntity<Page<BuildingDTO>> findAll(
            @RequestParam(value = "page", defaultValue="0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue="10") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue="ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue="CNPJ") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<BuildingDTO> buildings = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(buildings);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
