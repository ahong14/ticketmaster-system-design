package com.ticketmaster_system_design.ticketmaster_search_index_service.repositories;


import com.ticketmaster_system_design.ticketmaster_search_index_service.models.Event;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SearchEventRepository extends ElasticsearchRepository<Event, UUID> {

}
