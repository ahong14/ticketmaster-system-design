package com.ticketmaster_system_design.ticketmaster_event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
public class TicketmasterEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketmasterEventApplication.class, args);
	}

}
