package com.pluralsight.springboot.tickets.registration;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path="/registrations")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    public Registration create(@RequestBody @Valid Registration registration) {
        return registrationRepository.create(registration);
    }

    @GetMapping
    public Registration get(@RequestParam("ticketCode") String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + "is not found"));
    }


    @PutMapping
    public Registration update(@RequestBody Registration registration) {
        return registrationRepository.update(registration);
    }

    @DeleteMapping(path = "/{ticketCode}")
    public void delete(@RequestParam String ticketCode) {
        registrationRepository.deleteByTicketCode(ticketCode);
    }
}
