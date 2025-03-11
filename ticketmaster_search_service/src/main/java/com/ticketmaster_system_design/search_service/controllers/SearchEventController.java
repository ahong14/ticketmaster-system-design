package com.ticketmaster_system_design.search_service.controllers;

import com.ticketmaster_system_design.search_service.model.Event;
import com.ticketmaster_system_design.search_service.services.SearchEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchEventController {
    private final SearchEventServiceImpl searchEventService;

    @Autowired
    public SearchEventController(SearchEventServiceImpl searchEventService) {
        this.searchEventService = searchEventService;
    }

    @GetMapping(path="/name")
    ResponseEntity<List<Event>> searchEventsByName(@RequestParam String searchQuery) {
        List<Event> events = this.searchEventService.searchEvents(searchQuery);
        return ResponseEntity.ok(events);
    }

    @GetMapping
    ResponseEntity<List<Event>> searchEvents(@RequestParam String query) {
        List<Event> events = this.searchEventService.searchEventsSimpleQuery(query);
        return ResponseEntity.ok(events);
    }
}
