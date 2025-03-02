package com.ticketmaster_system_design.ticketmaster_event.controllers;

import com.ticketmaster_system_design.ticketmaster_event.models.Performer;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreatePerformerRequest;
import com.ticketmaster_system_design.ticketmaster_event.services.PerformerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/performers")
public class PerformerController {
    private final PerformerServiceImpl performerService;

    @Autowired
    public PerformerController(PerformerServiceImpl performerService) {
        this.performerService = performerService;
    }

    @GetMapping
    public ResponseEntity<List<Performer>> getPerformers() {
        List<Performer> performers = this.performerService.getPerformers();
        return ResponseEntity.ok(performers);
    }

    @PostMapping
    public ResponseEntity<Performer> createPerformer(@RequestBody CreatePerformerRequest createPerformerRequest) {
        Performer createdPerformer = this.performerService.createPerformer(createPerformerRequest);
        return ResponseEntity.ok(createdPerformer);
    }

    @GetMapping(path = "/{performerId}")
    public ResponseEntity<Performer> getPerformer(@PathVariable UUID performerId) {
        Performer foundPerformer = this.performerService.getPerformer(performerId);
        return ResponseEntity.ok(foundPerformer);
    }
}
