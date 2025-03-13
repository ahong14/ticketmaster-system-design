package com.ticketmaster_system_design.ticketmaster_booking_service.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;

    private List<UUID> tickets;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Booking(UUID userId, List<UUID> tickets, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.tickets = tickets;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<UUID> getTickets() {
        return tickets;
    }

    public void setTickets(List<UUID> tickets) {
        this.tickets = tickets;
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
}
