package com.api.odecasa.dtos;

import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Building;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BuildingDTO implements Serializable {

    private UUID id;
    private String CNPJ;
    private String address;
    private String zipcode;
    private String phone;
    private String email;
    Set<ApartmentDTO> apartments = new HashSet<>();

    public BuildingDTO() {}

    public BuildingDTO(UUID id, String CNPJ, String address, String zipcode, String phone, String email) {
        this.id = id;
        this.CNPJ = CNPJ;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
    }

    public BuildingDTO(Building building) {
        this.id = building.getId();
        this.CNPJ = building.getCNPJ();
        this.address = building.getAddress();
        this.zipcode = building.getZipcode();
        this.phone = building.getPhone();
        this.email = building.getEmail();
    }

    public BuildingDTO(Building building, Set<Apartment> apartments) {
        this(building);
        apartments.forEach(apt -> this.apartments.add(new ApartmentDTO(apt)));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ApartmentDTO> getApartments() {
        return apartments;
    }
}
