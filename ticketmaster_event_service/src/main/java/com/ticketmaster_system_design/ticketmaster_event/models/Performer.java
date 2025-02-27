package com.ticketmaster_system_design.ticketmaster_event.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Performer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
