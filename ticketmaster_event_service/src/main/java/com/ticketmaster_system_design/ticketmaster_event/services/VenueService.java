package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.Venue;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateVenueRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface VenueService {
    Venue createVenue(CreateVenueRequest createVenueRequest);
    List<Venue> getVenues();

    Venue getVenue(UUID venueId);
}
