package com.ticketmaster_system_design.ticketmaster_event.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ticketmaster_system_design.ticketmaster_event.models.Event;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateEventRequest;
import com.ticketmaster_system_design.ticketmaster_event.repositories.EventRepository;
import com.ticketmaster_system_design.ticketmaster_event.repositories.PerformerRepository;
import com.ticketmaster_system_design.ticketmaster_event.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "events")
public class EventServiceImpl implements EventService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EventRepository eventRepository;

    private final VenueRepository venueRepository;

    private final PerformerRepository performerRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${topic.name.producer}")
    private String topicName;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, VenueRepository venueRepository, PerformerRepository performerRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.performerRepository = performerRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<Event> getEvents() {
        return this.eventRepository.findAll();
    }

    @Override
    @Cacheable(key = "#eventId")
    public Event getEvent(UUID eventId) {
        Optional<Event> foundEvent = this.eventRepository.findById(eventId);
        if (foundEvent.isEmpty()) {
            throw new NoSuchElementException("Event ID not found");
        }

        return foundEvent.get();
    }

    @Override
    public Event createEvent(CreateEventRequest createEventRequest) throws JsonProcessingException {
        UUID venueId = createEventRequest.getVenueId();
        UUID performerId = createEventRequest.getPerformerId();

        if (!this.venueRepository.existsById(venueId)) {
            throw new IllegalArgumentException("Invalid venue id provided");
        }

        if (!this.performerRepository.existsById(performerId)) {
            throw new IllegalArgumentException("Invalid performer id provided");
        }

        LocalDateTime eventStartTime = createEventRequest.getEventStartTime();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Event newEvent = new Event(createEventRequest.getVenueId(),
                createEventRequest.getPerformerId(),
                createEventRequest.getName(),
                createEventRequest.getDescription(),
                createEventRequest.getSize(),
                createEventRequest.getPrice()
        );

        newEvent.setEventStartTime(eventStartTime);
        newEvent.setCreatedAt(currentDateTime);
        newEvent.setUpdatedAt(currentDateTime);

        Event createdEvent = this.eventRepository.save(newEvent);
        // publish event to kafka after saving to db
        this.publishEventMessage(createdEvent);
        return createdEvent;
    }

    @Override
    public void publishEventMessage(Event event) throws JsonProcessingException {
        // publish message to kafka topic
        // convert event to JSON string
        ObjectWriter objectWriter = new ObjectMapper().registerModule(new JavaTimeModule()).writer();
        String eventJson = objectWriter.writeValueAsString(event);

        // publish JSON string to kafka topic
        logger.info("Sending kafka message: " + eventJson);
        logger.info("topic name: " + topicName);
        kafkaTemplate.send(topicName, eventJson);
    }
}
