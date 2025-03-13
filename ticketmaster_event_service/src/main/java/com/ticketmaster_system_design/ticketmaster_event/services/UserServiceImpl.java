package com.ticketmaster_system_design.ticketmaster_event.services;

import com.ticketmaster_system_design.ticketmaster_event.models.TicketmasterUser;
import com.ticketmaster_system_design.ticketmaster_event.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     *
     * @param email
     * @param firstName
     * @param lastName
     * @return TicketmasterUser newly created user
     */
    @Override
    public TicketmasterUser createUser(String email, String firstName, String lastName) {
        LocalDateTime currentTime = LocalDateTime.now();
        TicketmasterUser newUser = new TicketmasterUser(email, firstName, lastName, currentTime, currentTime);
        return this.userRepository.save(newUser);
    }

    /**
     *
     * @param userId
     * @return TicketmasterUser found user
     */
    @Override
    public TicketmasterUser getUser(UUID userId) {
        Optional<TicketmasterUser> foundUser = this.userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            throw new NoSuchElementException("User ID not found");
        }

        return foundUser.get();
    }
}
