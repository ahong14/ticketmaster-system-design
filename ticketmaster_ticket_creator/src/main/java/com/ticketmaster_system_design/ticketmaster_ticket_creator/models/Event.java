package com.ticketmaster_system_design.ticketmaster_ticket_creator.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
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

    private int size;

    @OneToMany
    private ArrayList<Ticket> tickets;

    public Event() {

    }

    public Event(UUID venueId, UUID performerId, String name, String description) {
        this.venueId = venueId;
        this.performerId = performerId;
        this.name = name;
        this.description = description;
    }

    public Event(UUID performerId, String name, String description, int size) {
        this.performerId = performerId;
        this.name = name;
        this.description = description;
        this.size = size;
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

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
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
