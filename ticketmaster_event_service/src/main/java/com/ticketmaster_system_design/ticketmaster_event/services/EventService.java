package com.ticketmaster_system_design.ticketmaster_event.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ticketmaster_system_design.ticketmaster_event.models.Event;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateEventRequest;

import java.util.List;
import java.util.UUID;

public interface EventService {
    List<Event> getEvents();

    Event getEvent(UUID eventId);

    Event createEvent(CreateEventRequest createEventRequest) throws JsonProcessingException;

   void deleteEvent(UUID eventId);

    void publishEventMessage(Event event) throws JsonProcessingException;


}
