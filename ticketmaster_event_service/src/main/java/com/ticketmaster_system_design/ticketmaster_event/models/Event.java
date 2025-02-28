package com.ticketmaster_system_design.ticketmaster_event.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID venueId;

    private UUID performerId;

    private String name;

    private String description;

    public Event() {

    }

    public Event(UUID venueId, UUID performerId, String name, String description) {
        this.venueId = venueId;
        this.performerId = performerId;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getVenueId() {
        return venueId;
    }

    public void setVenueId(UUID venueId) {
        this.venueId = venueId;
    }

    public UUID getPerformerId() {
        return performerId;
    }

    public void setPerformerId(UUID performerId) {
        this.performerId = performerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", venueId=" + venueId +
                ", performerId=" + performerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
