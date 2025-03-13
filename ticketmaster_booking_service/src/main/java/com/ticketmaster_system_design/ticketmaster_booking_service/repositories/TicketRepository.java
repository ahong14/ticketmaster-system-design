package com.ticketmaster_system_design.ticketmaster_booking_service.repositories;

import com.ticketmaster_system_design.ticketmaster_booking_service.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
