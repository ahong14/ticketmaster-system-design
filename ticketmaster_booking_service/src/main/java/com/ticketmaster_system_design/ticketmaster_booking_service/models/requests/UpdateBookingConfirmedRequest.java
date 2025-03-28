package com.ticketmaster_system_design.ticketmaster_booking_service.models.requests;

import java.util.UUID;

public class UpdateBookingConfirmedRequest {
    private String paymentId;
    private UUID bookingId;

    public UpdateBookingConfirmedRequest(String paymentId, UUID bookingId) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}
