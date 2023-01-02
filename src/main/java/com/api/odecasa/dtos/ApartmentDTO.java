package com.api.odecasa.dtos;

import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Building;
import com.api.odecasa.entities.Tenant;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ApartmentDTO implements Serializable {

    private UUID id;
    private String apt;
    private Boolean occupied;

    private Set<TenantDTO> tenants = new HashSet<>();
    private BuildingDTO building;

    public ApartmentDTO() {}

    public ApartmentDTO(UUID uuid, String apt, Boolean occupied, BuildingDTO building) {
        this.id = uuid;
        this.apt = apt;
        this.occupied = occupied;
        this.building = building;
    }

    public ApartmentDTO(Apartment apartment) {
        this.id = apartment.getId();
        this.apt = apartment.getApt();
        this.occupied = apartment.getOccupied();
    }

    public ApartmentDTO(Apartment apartment, Building building) {
        this(apartment);
        this.building = new BuildingDTO(building);
    }

    public ApartmentDTO(Apartment apartment, Set<Tenant> tenants) {
        this(apartment);
        for (Tenant t: tenants){
            this.tenants.add(new TenantDTO(t));
        }
    }

    public UUID getUuid() {
        return id;
    }

    public void setUuid(UUID uuid) {
        this.id = uuid;
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

    public Set<TenantDTO> getTenants() {
        return tenants;
    }

    public void setTenants(Set<TenantDTO> tenants) {
        this.tenants = tenants;
    }
}
