package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.Performer;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreatePerformerRequest;
import com.ticketmaster_system_design.ticketmaster_event.repositories.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PerformerServiceImpl implements PerformerService {
    private final PerformerRepository performerRepository;

    @Autowired
    public PerformerServiceImpl(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    @Override
    public List<Performer> getPerformers() {
        return this.performerRepository.findAll();
    }

    @Override
    public Performer createPerformer(CreatePerformerRequest createPerformerRequest) {
        Performer newPerformer = new Performer(createPerformerRequest.getName(), createPerformerRequest.getDescription());
        return this.performerRepository.save(newPerformer);
    }

    @Override
    public Performer getPerformer(UUID performerId) {
        Optional<Performer> foundPerformer = this.performerRepository.findById(performerId);
        if (foundPerformer.isEmpty()) {
            throw new NoSuchElementException("Performer ID not found");
        }
        return foundPerformer.get();
    }
}
