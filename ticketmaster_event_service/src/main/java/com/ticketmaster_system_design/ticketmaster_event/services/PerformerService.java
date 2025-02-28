package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.Performer;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreatePerformerRequest;

import java.util.List;
import java.util.UUID;

public interface PerformerService {
    List<Performer> getPerformers();
    Performer createPerformer(CreatePerformerRequest createPerformerRequest);
    Performer getPerformer(UUID performerId);
}
