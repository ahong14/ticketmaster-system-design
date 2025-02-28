package com.ticketmaster_system_design.ticketmaster_event.models.requests;

import java.util.UUID;

public class CreateEventRequest {
    private UUID venueId;

    private UUID performerId;

    private String name;

    private String description;

    public CreateEventRequest() {}

    public CreateEventRequest(UUID venueId, UUID performerId, String name, String description) {
        this.venueId = venueId;
        this.performerId = performerId;
        this.name = name;
        this.description = description;
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
        return "CreateEventRequest{" +
                "venueId=" + venueId +
                ", performerId=" + performerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
