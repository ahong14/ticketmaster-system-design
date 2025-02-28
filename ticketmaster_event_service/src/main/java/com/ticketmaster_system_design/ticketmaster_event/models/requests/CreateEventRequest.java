package com.ticketmaster_system_design.ticketmaster_event.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateEventRequest {
    private UUID venueId;

    private UUID performerId;

    private String name;

    private String description;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm")
    private LocalDateTime eventStartTime;

    private int size;
    public CreateEventRequest() {}

    public CreateEventRequest(UUID venueId, UUID performerId, String name, String description, LocalDateTime eventStartTime, int size) {
        this.venueId = venueId;
        this.performerId = performerId;
        this.name = name;
        this.description = description;
        this.eventStartTime = eventStartTime;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    @Override
    public String toString() {
        return "CreateEventRequest{" +
                "venueId=" + venueId +
                ", performerId=" + performerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", eventStartTime=" + eventStartTime +
                ", size=" + size +
                '}';
    }
}
