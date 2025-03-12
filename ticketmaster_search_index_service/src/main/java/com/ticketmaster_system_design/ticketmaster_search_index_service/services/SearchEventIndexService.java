package com.ticketmaster_system_design.ticketmaster_search_index_service.services;

import com.ticketmaster_system_design.ticketmaster_search_index_service.models.Event;

public interface SearchEventIndexService {

    Event indexDocument(Event document);
}
