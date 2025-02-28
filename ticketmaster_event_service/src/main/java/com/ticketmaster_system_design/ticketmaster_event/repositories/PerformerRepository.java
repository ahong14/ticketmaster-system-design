package com.ticketmaster_system_design.ticketmaster_event.repositories;

import com.ticketmaster_system_design.ticketmaster_event.models.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerformerRepository extends JpaRepository<Performer, UUID> {
}
