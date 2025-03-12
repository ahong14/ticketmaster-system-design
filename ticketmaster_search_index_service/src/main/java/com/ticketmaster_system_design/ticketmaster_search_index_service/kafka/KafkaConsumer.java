package com.ticketmaster_system_design.ticketmaster_search_index_service.kafka;

import com.ticketmaster_system_design.ticketmaster_search_index_service.models.Event;
import com.ticketmaster_system_design.ticketmaster_search_index_service.services.SearchEventIndexServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SearchEventIndexServiceImpl searchEventIndexService;

    @Autowired
    public KafkaConsumer(SearchEventIndexServiceImpl searchEventIndexService) {
        this.searchEventIndexService = searchEventIndexService;
    }

    /**
     *
     * @param eventMessage
     */
    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "searchIndexEventKafkaListenerContainerFactory", groupId = "search_index_event")
    public void consume(Event eventMessage) {
        logger.info("Received message: " + eventMessage.toString());
        logger.info("Event ID: " + eventMessage.getId());

        // index / reindex document
        this.searchEventIndexService.indexDocument(eventMessage);
    }
}
