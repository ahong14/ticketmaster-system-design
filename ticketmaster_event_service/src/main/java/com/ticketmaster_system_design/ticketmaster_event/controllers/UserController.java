package com.ticketmaster_system_design.ticketmaster_event.controllers;

import com.ticketmaster_system_design.ticketmaster_event.models.TicketmasterUser;
import com.ticketmaster_system_design.ticketmaster_event.models.requests.CreateUserRequest;
import com.ticketmaster_system_design.ticketmaster_event.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TicketmasterUser> createUser(@RequestBody CreateUserRequest createUserRequest) {
        TicketmasterUser createdUser = this.userService.createUser(createUserRequest.getFirstName(), createUserRequest.getFirstName(), createUserRequest.getLastName());
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<TicketmasterUser> getUser(@PathVariable UUID userId) {
        TicketmasterUser foundUser = this.userService.getUser(userId);
        return ResponseEntity.ok(foundUser);
    }

}
