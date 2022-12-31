package com.api.odecasa.dtos;

import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Building;

import java.io.Serializable;
import java.util.UUID;

public class ApartmentDTO implements Serializable {

    private UUID uuid;
    private String apt;
    private Boolean occupied;
    //private Set<Tenant> tenants = new HashSet<>();
    private BuildingDTO building;

    //Ajustar para receber o Inquilino em segudia

    public ApartmentDTO() {}

    public ApartmentDTO(UUID uuid, String apt, Boolean occupied, BuildingDTO building) {
        this.uuid = uuid;
        this.apt = apt;
        this.occupied = occupied;
        this.building = building;
    }

    public ApartmentDTO(Apartment apartment) {
        this.uuid = apartment.getUuid();
        this.apt = apartment.getApt();
        this.occupied = apartment.getOccupied();
    }

    public ApartmentDTO(Apartment apartment, Building building) {
        this(apartment);
        this.building = new BuildingDTO(building);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public BuildingDTO getBuilding() {
        return building;
    }

    public void setBuilding(BuildingDTO building) {
        this.building = building;
    }
}
