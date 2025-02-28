package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.Event;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateEventRequest;
import com.ticketmaster_system_design.ticketmaster_event.repositories.EventRepository;
import com.ticketmaster_system_design.ticketmaster_event.repositories.PerformerRepository;
import com.ticketmaster_system_design.ticketmaster_event.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "events")
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    private final VenueRepository venueRepository;

    private final PerformerRepository performerRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, VenueRepository venueRepository, PerformerRepository performerRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.performerRepository = performerRepository;
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
    public Event createEvent(CreateEventRequest createEventRequest) {
        UUID venueId = createEventRequest.getVenueId();
        UUID performerId = createEventRequest.getPerformerId();

        if (!this.venueRepository.existsById(venueId)) {
            throw new IllegalArgumentException("Invalid venue id provided");
        }

        if (!this.performerRepository.existsById(performerId)) {
            throw new IllegalArgumentException("Invalid performer id provided");
        }

        Event newEvent = new Event(createEventRequest.getVenueId(),
                createEventRequest.getPerformerId(),
                createEventRequest.getName(),
                createEventRequest.getDescription());
        return this.eventRepository.save(newEvent);
    }
}
