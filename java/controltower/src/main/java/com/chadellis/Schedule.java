package com.chadellis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Schedule {
    private List<Itinerary> itineraries = new ArrayList<>();

    public void addItinerary(Itinerary i) {
        this.itineraries.add(i);
    }

    public List<Itinerary> getByOrigin(Airport airport) {
        return this.itineraries.stream().filter(i -> i.getOrigin().equals(airport)).toList();
    }

    public List<Itinerary> getByDestination(Airport airport) {
        return this.itineraries.stream().filter(i -> i.getDestination().equals(airport)).toList();
    }

    public Optional<Itinerary> getNextDepartureFrom(Airport airport) {
        return getUpcomingOutbound(airport).stream().findFirst();
    }

    public Optional<Itinerary> getNextArrivalTo(Airport airport) {
        return getUpcomingInbound(airport).stream().findFirst();
    }

    public List<Itinerary> getUpcomingFlights(Airport airport) {
        return Stream.concat(getUpcomingInbound(airport).stream(), getUpcomingOutbound(airport).stream())
            .sorted(new Comparator<Itinerary>() {
                public int compare(Itinerary i1, Itinerary i2) {
                    LocalDateTime t1 = i1.getOrigin().equals(airport) ? i1.getDeparture() : i1.getArrival(); 
                    LocalDateTime t2 = i2.getOrigin().equals(airport) ? i2.getDeparture() : i2.getArrival();
                    return t1.compareTo(t2);
                }
            })
            .toList();
    }

    public List<Itinerary> getUpcomingFlightsLambda(Airport airport) {
        return Stream.concat(getUpcomingInbound(airport).stream(), getUpcomingOutbound(airport).stream())
            .sorted((i1, i2) -> {
                LocalDateTime t1 = i1.getOrigin().equals(airport) ? i1.getDeparture() : i1.getArrival(); 
                LocalDateTime t2 = i2.getOrigin().equals(airport) ? i2.getDeparture() : i2.getArrival();
                return t1.compareTo(t2);
            })
            .toList();
    }

    private List<Itinerary> getUpcomingInbound(Airport airport) {
        return getByDestination(airport).stream()
            .sorted((i1, i2) -> i1.getArrival().compareTo(i2.getArrival()))
            .filter(i -> i.getArrival().isAfter(LocalDateTime.now()))
            .toList();
    }

    private List<Itinerary> getUpcomingOutbound(Airport airport) {
        return getByOrigin(airport).stream()
            .sorted((i1, i2) -> i1.getDeparture().compareTo(i2.getDeparture()))
            .filter(i -> i.getDeparture().isAfter(LocalDateTime.now()))
            .toList();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule");
        for (Itinerary i: this.itineraries) {
            sb.append("\n" + i);
        }
        return sb.toString();
    }
}
