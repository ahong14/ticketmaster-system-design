package com.ticketmaster_system_design.ticketmaster_event.exceptions;

import java.time.LocalDateTime;

public class TicketmasterExceptionDetails {
    private LocalDateTime errorTimestamp;
    private String errorMessage;

    public TicketmasterExceptionDetails(LocalDateTime errorTimestamp, String errorMessage) {
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