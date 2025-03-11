package com.ticketmaster_system_design.search_service.repositories;

import com.ticketmaster_system_design.search_service.model.Event;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SearchEventRepository extends ElasticsearchRepository<Event, UUID> {
    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    List<Event> findByName(String eventName);

    @Query("{\"simple_query_string\": {\"query\": \"?0\", \"fields\": [\"name\", \"description\"], \"default_operator\": \"or\"}}")
    List<Event> findBySimpleQuery(String query);
}
