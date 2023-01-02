package com.api.odecasa.dtos;

import com.api.odecasa.entities.Apartment;
import com.api.odecasa.entities.Tenant;

import java.io.Serializable;
import java.util.UUID;

public class TenantDTO implements Serializable {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String bio;
    private ApartmentDTO apartment;
    //Set<AdvertisingDTO> ads = new HashSet<>();

    public TenantDTO() {}

    public TenantDTO(UUID id, String name, String email, String phone, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
    }

    public TenantDTO(Tenant tenant) {
        this.id = tenant.getId();
        this.name = tenant.getName();
        this.email = tenant.getEmail();
        this.phone = tenant.getPhone();
        this.bio = tenant.getBio();
    }

    public TenantDTO(Tenant tenant, Apartment apartment) {
        this(tenant);
        this.apartment = new ApartmentDTO(apartment);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ApartmentDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentDTO apartment) {
        this.apartment = apartment;
    }
}
