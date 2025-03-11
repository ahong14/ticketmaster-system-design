package com.ticketmaster_system_design.search_service.services;

import com.ticketmaster_system_design.search_service.model.Event;

import java.util.List;

public interface SearchEventService {
    List<Event> searchEvents(String eventName);

    List<Event> searchEventsSimpleQuery(String query);
}
