package com.ticketmaster_system_design.ticketmaster_ticket_creator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TicketmasterTicketCreatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketmasterTicketCreatorApplication.class, args);
	}

}
