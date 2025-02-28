package com.ticketmaster_system_design.ticketmaster_event.repositories;

import com.ticketmaster_system_design.ticketmaster_event.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenueRepository extends JpaRepository<Venue, UUID> {
}
