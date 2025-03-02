package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.Ticket;

import java.util.UUID;

public interface TicketService {
    Ticket getTicket(UUID ticketId);
}
