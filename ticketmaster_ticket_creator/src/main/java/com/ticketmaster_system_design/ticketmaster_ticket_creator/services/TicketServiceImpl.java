package com.ticketmaster_system_design.ticketmaster_ticket_creator.services;

import com.ticketmaster_system_design.ticketmaster_ticket_creator.models.Event;
import com.ticketmaster_system_design.ticketmaster_ticket_creator.models.Ticket;
import com.ticketmaster_system_design.ticketmaster_ticket_creator.models.TicketEnum;
import com.ticketmaster_system_design.ticketmaster_ticket_creator.repositories.EventRepository;
import com.ticketmaster_system_design.ticketmaster_ticket_creator.repositories.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TicketRepository ticketRepository;

    private final EventRepository eventRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createTickets(Event event) {
        // create number of tickets based on event size
        int size = event.getSize();
        double ticketPrice = event.getPrice();
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Ticket newTicket = new Ticket(event.getId(), "Event Seat", TicketEnum.AVAILABLE, ticketPrice);
            this.ticketRepository.save(newTicket);
            logger.info("Created ticket: " + newTicket.getId());
            tickets.add(newTicket);
        }

        event.setTickets(tickets);
        return this.eventRepository.save(event);
    }
}
