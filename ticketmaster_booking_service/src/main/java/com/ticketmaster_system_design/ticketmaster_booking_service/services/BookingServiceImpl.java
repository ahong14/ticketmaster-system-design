package com.ticketmaster_system_design.ticketmaster_booking_service.services;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.ticketmaster_system_design.ticketmaster_booking_service.models.Booking;
import com.ticketmaster_system_design.ticketmaster_booking_service.repositories.BookingRepository;
import jakarta.transaction.Transactional;
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

    private final StripeService stripeService;

    private final String TICKET_KEY = "ticket_key_";

    private final Duration LOCK_TTL = Duration.ofMinutes(10);

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, TicketServiceImpl ticketService, RedisTemplate<String, String> redisTemplate, StripeServiceImpl stripeService) {
        this.bookingRepository = bookingRepository;
        this.ticketService = ticketService;
        this.redisTemplate = redisTemplate;
        this.stripeService = stripeService;
    }

    /**
     *
     * @param userId - user id booking tickets
     * @param tickets - list of ticket ids being reserved
     * @return Reserved booking
     */
    @Override
    public Booking reserveBooking(UUID userId, List<UUID> tickets) {
        // reserve tickets to user id in redis cache, lock tickets for TTL
        for (UUID ticket : tickets) {
            // if tickets not already booked
            if (ticketService.isTicketBooked(ticket)) {
                throw new IllegalArgumentException("Ticket ID already booked: " + ticket);
            }

            // check if tickets locked by another booking
            Boolean lockAcquired = this.redisTemplate.opsForValue().setIfAbsent(TICKET_KEY + ticket.toString(), userId.toString(), LOCK_TTL);
            if (lockAcquired != null && lockAcquired) {
                log.info("Ticket ID locked: {}", ticket);
            } else {
                log.error("Ticket ID already locked: {}", ticket);
                throw new IllegalArgumentException("Ticket ID already reserved: " + ticket);
            }
        }

        // create new booking with in progress status
        LocalDateTime currentTime = LocalDateTime.now();
        Booking newBooking = new Booking(userId, tickets, currentTime, currentTime, Booking.BookingStatus.IN_PROGRESS);
        return this.bookingRepository.save(newBooking);
    }

    /**
     *
     * @param userId - user id booking tickets
     * @param tickets - list of tickets part of booking
     * @return Booking new booking created
     */
    @Transactional
    @Override
    public Booking createBooking(UUID userId, List<UUID> tickets) {
        LocalDateTime currentTime = LocalDateTime.now();
        Booking newBooking = new Booking(userId, tickets, currentTime, currentTime);
        this.bookingRepository.save(newBooking);
        // after booking created successfully, set ticket status to booked by user id
        for (UUID ticketId : tickets) {
            this.ticketService.updateTicketBooked(ticketId, userId);
            this.redisTemplate.opsForValue().getAndDelete(TICKET_KEY + ticketId);
        }
        return newBooking;
    }

    /**
     *
     * @param bookingId - booking id to look for
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
     * @param bookingId - booking id to delete
     */
    @Transactional
    @Override
    public void deleteBooking(UUID bookingId) {
        Optional<Booking> foundBooking = this.bookingRepository.findById(bookingId);
        if (foundBooking.isEmpty()) {
            throw new NoSuchElementException("Booking ID not found");
        }
        Booking deletedBooking = foundBooking.get();
        this.bookingRepository.delete(deletedBooking);

        // free up tickets, set to available after deleting booking
        for (UUID ticketId : deletedBooking.getTickets()) {
            this.ticketService.updateTicketAvailable(ticketId);
        }
    }

    /**
     *
     * @param userId - user id to look up bookings for
     * @return - list of bookings
     */
    @Override
    public List<Booking> getUserBookings(UUID userId) {
        return this.bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking updateBookingConfirmed(String paymentId, UUID bookingId) throws StripeException {
        // check for valid booking
        Optional<Booking> foundBooking = this.bookingRepository.findById(bookingId);
        if (foundBooking.isEmpty()) {
            throw new NoSuchElementException("Booking ID not found: " + bookingId);
        }

        Booking updatedBooking = foundBooking.get();

        if (updatedBooking.getBookingStatus() == Booking.BookingStatus.CONFIRMED) {
            throw new IllegalArgumentException("Booking already confirmed.");
        }

        // check if payment was successfully processed
        PaymentIntent paymentIntent = this.stripeService.findPayment(paymentId);

        if (paymentIntent == null || paymentIntent.getStatus() != "succeeded") {
            throw new IllegalArgumentException("Payment not successful, unable to confirm booking");
        }

        // update booking as confirmed
        updatedBooking.setBookingStatus(Booking.BookingStatus.CONFIRMED);
        return this.bookingRepository.save(updatedBooking);
    }
}