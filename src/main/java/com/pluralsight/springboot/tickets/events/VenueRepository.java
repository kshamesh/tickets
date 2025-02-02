package com.pluralsight.springboot.tickets.events;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class VenueRepository {

    private final List<Venue> venues = List.of(
            new Venue(202, "Globomantics Main Officer", "Test Street 325", "New York", "USA"),
            new Venue(203, "Sea View Hotel", "Beach Boulevard 863", "Los Angeles", "USA")
    );

    public Optional<Venue> findById(int id) {
        return venues.stream().filter(venue -> venue.id() == id).findAny();
    }
}
