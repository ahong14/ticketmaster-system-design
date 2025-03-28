package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.stripe.exception.StripeException;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    Booking reserveBooking(UUID userId, List<UUID> tickets);
    Booking createBooking(UUID userId, List<UUID> tickets);
    Booking getBooking(UUID bookingId);
    void deleteBooking(UUID bookingId);
    Booking updateBookingConfirmed(String paymentId, UUID bookingId) throws StripeException;
    List<Booking> getUserBookings(UUID userId);
}