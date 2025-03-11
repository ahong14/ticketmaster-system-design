package com.ticketmaster_system_design.ticketmaster_event.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ticketmaster_system_design.ticketmaster_event.models.Event;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateEventRequest;
import com.ticketmaster_system_design.ticketmaster_event.services.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = this.eventService.getEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable UUID eventId) {
        Event foundEvent = this.eventService.getEvent(eventId);
        return ResponseEntity.ok(foundEvent);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest createEventRequest) throws JsonProcessingException {
        Event createdEvent = this.eventService.createEvent(createEventRequest);
        return ResponseEntity.ok(createdEvent);
    }

    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID eventId) {
        this.eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
