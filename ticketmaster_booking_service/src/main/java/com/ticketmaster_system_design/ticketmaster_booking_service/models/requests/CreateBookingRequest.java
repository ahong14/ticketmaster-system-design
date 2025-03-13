package com.ticketmaster_system_design.ticketmaster_booking_service.models.requests;

import java.util.List;
import java.util.UUID;

public class CreateBookingRequest {
    private UUID userId;

    private List<UUID> tickets;

    public CreateBookingRequest(UUID userId, List<UUID> tickets) {
        this.userId = userId;
        this.tickets = tickets;
    }

    public CreateBookingRequest() {
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
}
