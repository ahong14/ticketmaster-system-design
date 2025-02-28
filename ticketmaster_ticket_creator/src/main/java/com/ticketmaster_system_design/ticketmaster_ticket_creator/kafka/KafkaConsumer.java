package com.ticketmaster_system_design.ticketmaster_ticket_creator.kafka;

import com.ticketmaster_system_design.ticketmaster_ticket_creator.services.TicketService;
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

    @Value(value = "${topic.name.consumer})")
    private String topicName;

    @Value(value = "${spring.kafka.consumer.group-id")
    private String groupId;

    private final TicketServiceImpl ticketService;

    @Autowired
    public KafkaConsumer(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @KafkaListener(topics = "ticket_creations", groupId = "ticket_creator")
    public void consume(String message) {
        logger.info("Received message: " + message);
        // Process the message
    }
}
