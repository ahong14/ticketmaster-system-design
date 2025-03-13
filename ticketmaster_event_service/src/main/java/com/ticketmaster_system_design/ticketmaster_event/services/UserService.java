package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.TicketmasterUser;

import java.util.UUID;

public interface UserService {
    TicketmasterUser createUser(String email, String firstName, String lastName);
    TicketmasterUser getUser(UUID userId);
}
