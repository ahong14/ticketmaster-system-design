package com.ticketmaster_system_design.ticketmaster_event.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID venueId;

    private UUID performerId;

    private String name;

    private String description;

    private int size;

    private double price;

    private LocalDateTime eventStartTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany
    private List<Ticket> tickets;

    public Event() {

    }

    public Event(UUID venueId, UUID performerId, String name, String description) {
        this.venueId = venueId;
        this.performerId = performerId;
        this.name = name;
        this.description = description;
    }

    public Event(UUID venueId, UUID performerId, String name, String description, int size, double price) {
        this.venueId = venueId;
        this.performerId = performerId;
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", venueId=" + venueId +
                ", performerId=" + performerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", eventStartTime=" + eventStartTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", tickets=" + tickets +
                '}';
    }
}
