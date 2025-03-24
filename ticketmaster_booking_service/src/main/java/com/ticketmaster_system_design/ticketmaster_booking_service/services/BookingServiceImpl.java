package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;
import com.ticketmaster_system_design.ticketmaster_booking_service.repositories.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final BookingRepository bookingRepository;

    private final TicketServiceImpl ticketService;

    private final RedisTemplate<String, String> redisTemplate;

    private final String TICKET_KEY = "ticket_key_";

    private final Duration LOCK_TTL = Duration.ofMinutes(10);

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, TicketServiceImpl ticketService, RedisTemplate<String, String> redisTemplate) {
        this.bookingRepository = bookingRepository;
        this.ticketService = ticketService;
        this.redisTemplate = redisTemplate;
    }

    /**
     *
     * @param userId
     * @param tickets
     */
    @Override
    public void reserveBooking(UUID userId, List<UUID> tickets) {
        // reserve tickets to user id in redis cache, lock tickets for TTL
        for (UUID ticket : tickets) {
            boolean lockAcquired = this.redisTemplate.opsForValue().setIfAbsent(TICKET_KEY + ticket.toString(), userId.toString(), LOCK_TTL);
            if (lockAcquired) {
                log.info("Ticket ID locked: {}", ticket);
            } else {
                log.error("Ticket ID already locked: {}", ticket);
                throw new IllegalArgumentException("Ticket ID already reserved: " + ticket);
            }
        }
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
        // after booking created successfully, set ticket status to booked by user id
        for (UUID ticketId : tickets) {
            this.ticketService.updateTicketBooked(ticketId, userId);
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