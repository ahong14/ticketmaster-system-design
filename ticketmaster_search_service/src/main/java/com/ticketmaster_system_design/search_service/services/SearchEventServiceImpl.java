package com.ticketmaster_system_design.search_service.services;

import com.ticketmaster_system_design.search_service.model.Event;
import com.ticketmaster_system_design.search_service.repositories.SearchEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchEventServiceImpl implements SearchEventService {
    private final SearchEventRepository searchEventRepository;

    @Autowired
    public SearchEventServiceImpl(SearchEventRepository searchEventRepository) {
        this.searchEventRepository = searchEventRepository;
    }

    @Override
    public List<Event> searchEvents(String eventName) {
        return this.searchEventRepository.findByName(eventName);
    }

    @Override
    public List<Event> searchEventsSimpleQuery(String query) {
        return this.searchEventRepository.findBySimpleQuery(query);
    }
}
