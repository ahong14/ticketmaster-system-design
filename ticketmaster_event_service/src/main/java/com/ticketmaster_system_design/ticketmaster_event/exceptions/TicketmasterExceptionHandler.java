package com.ticketmaster_system_design.ticketmaster_event.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class TicketmasterExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        TicketmasterExceptionDetails errorDetails = new TicketmasterExceptionDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<Object> handleNoSuchElementExceptions(Exception ex, WebRequest request) {
        TicketmasterExceptionDetails errorDetails = new TicketmasterExceptionDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleInvalidMethodArgumentException(Exception ex, WebRequest request) {
        TicketmasterExceptionDetails errorDetails = new TicketmasterExceptionDetails(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
