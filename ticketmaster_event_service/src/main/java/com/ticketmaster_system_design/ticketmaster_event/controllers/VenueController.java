package com.ticketmaster_system_design.ticketmaster_event.controllers;

import com.ticketmaster_system_design.ticketmaster_event.models.Venue;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateVenueRequest;
import com.ticketmaster_system_design.ticketmaster_event.services.VenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController implements Serializable {
    private final VenueServiceImpl venueService;

    @Autowired
    public VenueController(VenueServiceImpl venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<Venue>> getVenues() {
        List<Venue> venues = this.venueService.getVenues();
        return ResponseEntity.ok(venues);
    }

    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody CreateVenueRequest createVenueRequest) {
        Venue createdVenue = this.venueService.createVenue(createVenueRequest);
        return ResponseEntity.ok(createdVenue);
    }

    @GetMapping(path = "/{venueId}")
    public ResponseEntity<Venue> getVenue(@PathVariable UUID venueId) {
        Venue foundVenue = this.venueService.getVenue(venueId);
        return ResponseEntity.ok(foundVenue);
    }
}
