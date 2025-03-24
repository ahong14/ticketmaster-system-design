package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.ticketmaster_system_design.ticketmaster_booking_service.models.Ticket;

import java.util.UUID;

public interface TicketService {
    Ticket getTicket(UUID ticketId);

    Ticket updateTicketBooked(UUID ticketId, UUID userId);

    Ticket updateTicketAvailable(UUID ticketId);
}
