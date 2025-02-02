package com.pluralsight.springboot.tickets.events;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@RestController
public class EventController {

    private final OrganizerRepository organizerRepository;
    private final EventRepository eventRepository;
    private final ProductRepository productRepository;
    public EventController (OrganizerRepository organizerRepository,
                            EventRepository eventRepository,
                            ProductRepository productRepository) {

        this.organizerRepository = organizerRepository;
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
    }
    @GetMapping(path = "/organizers")
    /* @Operation(summary = "Get the list of all available organizers", description = "Returns a simple list")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = String.class))) */
    public List<Organizer> getOrganizers() {
        return organizerRepository.findAll();
    }
    @GetMapping(path = "/events")
    public List<Event> getEventsByOrganizer(@RequestParam("organizerId") int organizerId) {
        return eventRepository.findByOrganizerId (organizerId);
    }

    @GetMapping(path = "/events/{id}")
    public Event getEventById(@PathVariable("id") int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NoSuchElementException("Event with id " + eventId + " not found"));
    }

    @GetMapping(path = "/products")
    public List<Product> getProductsByEventId(@RequestParam("eventId") int eventId) {
        return productRepository.findByEventId(eventId);
    }


}
