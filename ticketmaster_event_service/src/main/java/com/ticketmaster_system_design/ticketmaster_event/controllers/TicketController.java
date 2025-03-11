package com.ticketmaster_system_design.ticketmaster_event.controllers;

import com.ticketmaster_system_design.ticketmaster_event.models.Ticket;
import com.ticketmaster_system_design.ticketmaster_event.services.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tickets")
public class TicketController {
    private final TicketServiceImpl ticketService ;

    @Autowired
    public TicketController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(path = "/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable UUID ticketId) {
        Ticket foundTicket = this.ticketService.getTicket(ticketId);
        return ResponseEntity.ok(foundTicket);
    }
}
