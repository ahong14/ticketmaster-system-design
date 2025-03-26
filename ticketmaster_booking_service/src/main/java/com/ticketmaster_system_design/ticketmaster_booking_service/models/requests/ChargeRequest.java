package com.ticketmaster_system_design.ticketmaster_booking_service.models.requests;


import java.util.UUID;

// reference: https://www.baeldung.com/java-stripe-api
public class ChargeRequest {
    private String description;
    private int amount;
    private UUID bookingId;

    public ChargeRequest(String description, int amount, UUID bookingId) {
        this.description = description;
        this.amount = amount;
        this.bookingId = bookingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}
