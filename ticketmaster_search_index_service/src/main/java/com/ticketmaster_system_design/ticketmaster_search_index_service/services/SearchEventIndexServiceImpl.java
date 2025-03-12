package com.ticketmaster_system_design.ticketmaster_search_index_service.services;

import com.ticketmaster_system_design.ticketmaster_search_index_service.models.Event;
import com.ticketmaster_system_design.ticketmaster_search_index_service.repositories.SearchEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchEventIndexServiceImpl implements SearchEventIndexService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SearchEventRepository searchEventRepository;

    @Autowired
    public SearchEventIndexServiceImpl(SearchEventRepository searchEventRepository) {
        this.searchEventRepository = searchEventRepository;
    }

    @Override
    public Event indexDocument(Event document) {
        try {
            return this.searchEventRepository.save(document);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
}
