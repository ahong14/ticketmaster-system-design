package com.ticketmaster_system_design.ticketmaster_ticket_creator.kafka;

import com.ticketmaster_system_design.ticketmaster_ticket_creator.models.Event;
import com.ticketmaster_system_design.ticketmaster_ticket_creator.repositories.EventRepository;
import com.ticketmaster_system_design.ticketmaster_ticket_creator.services.TicketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EventRepository eventRepository;

    @Value(value = "${topic.name.consumer})")
    private String topicName;

    @Value(value = "${spring.kafka.consumer.group-id")
    private String groupId;

    private final TicketServiceImpl ticketService;

    @Autowired
    public KafkaConsumer(TicketServiceImpl ticketService, EventRepository eventRepository) {
        this.ticketService = ticketService;
        this.eventRepository = eventRepository;
    }

    // kafka consumer, function that executes when a message is found
    // topics - topic to consume from
    // containerFactory - which
    // groupId

    /**
     *
     * @param eventMessage
     */
    @KafkaListener(topics = "ticket_creations", containerFactory = "ticketKafkaListenerContainerFactory", groupId = "ticket_creator")
    public void consume(Event eventMessage) {
        logger.info("Received message: " + eventMessage.toString());
        logger.info("Event ID: " + eventMessage.getId());

        // Process the message, create tickets for event
        Event updatedEvent = this.ticketService.createTickets(eventMessage);

        try {
            updatedEvent = this.eventRepository.save(updatedEvent);
            logger.info("Created tickets for event id: " + updatedEvent.getId());
        } catch (Exception exception) {
            logger.error("Exception during event creation: " + exception.getMessage());
        }
    }
}
