package com.ticketmaster_system_design.ticketmaster_booking_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID eventId;

    private String seat;

    private TicketEnum status;
    private double price;

    private UUID userId;

    private UUID bookingId;

    public Ticket() {
    }

    public Ticket(UUID eventId, String seat, TicketEnum status, double price, UUID userId) {
        this.eventId = eventId;
        this.seat = seat;
        this.status = status;
        this.price = price;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public TicketEnum getStatus() {
        return status;
    }

    public void setStatus(TicketEnum status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", seat='" + seat + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}