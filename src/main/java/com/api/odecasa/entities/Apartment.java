package com.api.odecasa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name="tb_apartment")
public class Apartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false, length = 6)
    private String apt;

    @Column(nullable = false)
    private Boolean occupied;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @OneToMany(mappedBy = "apartment")
    private Set<Tenant> tenants = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public Apartment(){}

    public Apartment(UUID uuid, String apt, Boolean occupied) {
        this.uuid = uuid;
        this.apt = apt;
        this.occupied = occupied;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

    public Set<Tenant> getTenants() {
        return tenants;
    }

    public Building getBuilding() {
        return building;
    }
}
