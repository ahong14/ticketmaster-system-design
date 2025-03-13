package com.ticketmaster_system_design.ticketmaster_event.repositories;

import com.ticketmaster_system_design.ticketmaster_event.models.TicketmasterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<TicketmasterUser, UUID> {
}
