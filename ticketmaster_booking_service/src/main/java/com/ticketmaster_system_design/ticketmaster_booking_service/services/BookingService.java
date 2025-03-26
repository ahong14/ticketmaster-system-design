package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    void reserveBooking(UUID userId, List<UUID> tickets);
    Booking createBooking(UUID userId, List<UUID> tickets);
    Booking getBooking(UUID bookingId);
    void deleteBooking(UUID bookingId);
    List<Booking> getUserBookings(UUID userId);
}