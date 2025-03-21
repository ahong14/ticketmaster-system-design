package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.Ticket;
import com.ticketmaster_system_design.ticketmaster_event.models.TicketEnum;
import com.ticketmaster_system_design.ticketmaster_event.repositories.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket getTicket(UUID ticketId) {
        Optional<Ticket> foundTicket = this.ticketRepository.findById(ticketId);
        if (foundTicket.isEmpty()) {
            throw new NoSuchElementException("Ticket ID not found");
        }
        return foundTicket.get();
    }

    @Override
    public Ticket updateTicketBooked(UUID ticketId) {
        Optional<Ticket> foundTicket = this.ticketRepository.findById(ticketId);
        if (foundTicket.isEmpty()) {
            throw new NoSuchElementException("Ticket ID not found");
        }
        Ticket updatedTicket = foundTicket.get();
        updatedTicket.setStatus(TicketEnum.BOOKED);
        return this.ticketRepository.save(updatedTicket);
    }

    @Override
    public Ticket updateTicketAvailable(UUID ticketId) {
        Optional<Ticket> foundTicket = this.ticketRepository.findById(ticketId);
        if (foundTicket.isEmpty()) {
            throw new NoSuchElementException("Ticket ID not found");
        }
        Ticket updatedTicket = foundTicket.get();
        updatedTicket.setStatus(TicketEnum.AVAILABLE);
        return this.ticketRepository.save(updatedTicket);
    }
}
