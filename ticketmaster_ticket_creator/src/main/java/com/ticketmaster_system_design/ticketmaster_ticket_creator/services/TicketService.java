package com.ticketmaster_system_design.ticketmaster_ticket_creator.services;

import com.ticketmaster_system_design.ticketmaster_ticket_creator.models.Event;
import com.ticketmaster_system_design.ticketmaster_ticket_creator.models.Ticket;

public interface TicketService {
    Event createTickets(Event event);
}
