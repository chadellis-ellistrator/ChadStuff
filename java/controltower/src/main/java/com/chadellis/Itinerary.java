package com.chadellis;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Itinerary {
    private static Legs legs = new Legs();
    private Airport origin;
    private Airport destination;
    private CarrierPlane carrierPlane;
    private LocalDateTime departure;
    private LocalDateTime arrival;

    public Itinerary(Airport origin, Airport destination, CarrierPlane carrierPlane, LocalDateTime departure) {
        this.origin = origin;
        this.destination = destination;
        this.carrierPlane = carrierPlane;
        this.departure = departure;
        this.setEstimatedArrival();
    }

    private void setEstimatedArrival() {
        // Legs must exist as they have distance and duration
        this.arrival = departure.plusMinutes(
            legs.getLeg(origin, destination).orElseThrow(() -> new IllegalArgumentException("Leg does not exist"))
                .durationInMinutes()
        );
    }

    public Airport getOrigin() {
        return this.origin;
    }

    public Airport getDestination() {
        return this.destination;
    }

    public LocalDateTime getDeparture() {
        return this.departure;
    }

    public LocalDateTime getArrival() {
        return this.arrival;
    }

    public String toString() {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        sb.append(this.origin.getIdentifier() + "-" + this.destination.getIdentifier());
        sb.append(" : " + this.carrierPlane);
        sb.append(" : " + this.departure + "-" + this.arrival);
        if (this.departure.isBefore(now)) {
            if (this.arrival.isBefore(now)) {
                sb.append(" - ARRIVED");
            } else {
                sb.append(" - DEPARTED, ARRIVING IN " + getDifference(now, this.arrival));
            }
        } else {
            sb.append(" - DEPARTING IN " + getDifference(now, this.departure));
        }
        return sb.toString();
    }

    private String getDifference(LocalDateTime l1, LocalDateTime l2) {
        long hoursBetween = Math.abs(ChronoUnit.HOURS.between(l1, l2));
        long minutesBetween = Math.abs(ChronoUnit.MINUTES.between(l1.plusHours(hoursBetween), l2));
        return hoursBetween + " HOURS, " + minutesBetween + " MINUTES";
    }
}
