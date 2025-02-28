package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.Venue;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateVenueRequest;
import com.ticketmaster_system_design.ticketmaster_event.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "venues")
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue createVenue(CreateVenueRequest createVenueRequest) {
        Venue newVenue = new Venue(createVenueRequest.getDescription(),
                createVenueRequest.getLocation(),
                createVenueRequest.getCoordinates(),
                createVenueRequest.getSeats());
        LocalDateTime currentDateTime = LocalDateTime.now();
        newVenue.setCreatedAt(currentDateTime);
        newVenue.setUpdatedAt(currentDateTime);
        return this.venueRepository.save(newVenue);
    }

    @Override
    public List<Venue> getVenues() {
        return this.venueRepository.findAll();
    }

    @Override
    @Cacheable(key = "#venueId")
    public Venue getVenue(UUID venueId) {
        Optional<Venue> foundVenue = this.venueRepository.findById(venueId);
        if (foundVenue.isEmpty()) {
            throw new NoSuchElementException("Venue ID not found");
        }
        return foundVenue.get();
    }
}
