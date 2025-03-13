package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;
import com.ticketmaster_system_design.ticketmaster_booking_service.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    private final TicketServiceImpl ticketService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, TicketServiceImpl ticketService) {
        this.bookingRepository = bookingRepository;
        this.ticketService = ticketService;
    }

    /**
     *
     * @param userId
     * @param tickets
     */
    @Override
    public void reserveBooking(UUID userId, List<UUID> tickets) {
        // TODO reserve tickets to user id in redis cache, lock tickets for TTL

    }

    /**
     *
     * @param userId
     * @param tickets
     * @return Booking new booking created
     */
    @Override
    public Booking createBooking(UUID userId, List<UUID> tickets) {
        LocalDateTime currentTime = LocalDateTime.now();
        Booking newBooking = new Booking(userId, tickets, currentTime, currentTime);
        this.bookingRepository.save(newBooking);
        // after booking created successfully, set ticket status to booked
        for (UUID ticketId : tickets) {
            this.ticketService.updateTicketBooked(ticketId);
        }
        return newBooking;
    }

    /**
     *
     * @param bookingId
     * @return Booking found booking
     */
    @Override
    public Booking getBooking(UUID bookingId) {
        Optional<Booking> foundBooking = this.bookingRepository.findById(bookingId);
        if (foundBooking.isEmpty()) {
            throw new NoSuchElementException("Booking ID not found");
        }

        return foundBooking.get();
    }

    /**
     *
     * @param bookingId
     */
    @Override
    public void deleteBooking(UUID bookingId) {
        Optional<Booking> foundBooking = this.bookingRepository.findById(bookingId);
        if (foundBooking.isEmpty()) {
            throw new NoSuchElementException("Booking ID not found");
        }
        Booking deletedBooking = foundBooking.get();
        this.bookingRepository.delete(deletedBooking);
        for (UUID ticketId : deletedBooking.getTickets()) {
            this.ticketService.updateTicketAvailable(ticketId);
        }
    }
}