package com.ticketmaster_system_design.ticketmaster_booking_service.controllers;


import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.requests.CreateBookingRequest;
import com.ticketmaster_system_design.ticketmaster_booking_service.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(path = "/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable UUID bookingId) {
        Booking foundBooking = this.bookingService.getBooking(bookingId);
        return ResponseEntity.ok(foundBooking);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody CreateBookingRequest createBookingRequest) {
        Booking createdBooking = this.bookingService.createBooking(createBookingRequest.getUserId(), createBookingRequest.getTickets());
        return ResponseEntity.ok(createdBooking);
    }

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable UUID userId) {
        List<Booking> userBookings = this.bookingService.getUserBookings(userId);
        return ResponseEntity.ok(userBookings);
    }

    @PostMapping(path = "/reserve")
    public ResponseEntity<String> reserveBooking(@RequestBody CreateBookingRequest createBookingRequest) {
        this.bookingService.reserveBooking(createBookingRequest.getUserId(), createBookingRequest.getTickets());
        return ResponseEntity.ok("Tickets reserved.");
    }

    @DeleteMapping(path = "/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        this.bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
