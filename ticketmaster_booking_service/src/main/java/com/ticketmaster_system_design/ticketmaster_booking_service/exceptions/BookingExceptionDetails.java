package com.ticketmaster_system_design.ticketmaster_booking_service.exceptions;

import java.time.LocalDateTime;

public class BookingExceptionDetails {
    private LocalDateTime errorTimestamp;
    private String errorMessage;

    public BookingExceptionDetails(LocalDateTime errorTimestamp, String errorMessage) {
        this.errorTimestamp = errorTimestamp;
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getErrorTimestamp() {
        return errorTimestamp;
    }

    public void setErrorTimestamp(LocalDateTime errorTimestamp) {
        this.errorTimestamp = errorTimestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
