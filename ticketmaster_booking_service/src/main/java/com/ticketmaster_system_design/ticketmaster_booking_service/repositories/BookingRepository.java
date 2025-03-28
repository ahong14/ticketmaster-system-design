package com.ticketmaster_system_design.ticketmaster_booking_service.repositories;

import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUserId(UUID userId);
}
