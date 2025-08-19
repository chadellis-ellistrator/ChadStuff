package com.chadellis;

import java.time.LocalDateTime;
import java.util.List;

public class ControlTower {
    private Schedule schedule;
    private static ControlTower instance;

    public static ControlTower getInstance() {
        if (ControlTower.instance == null) {
            ControlTower.instance = new ControlTower();
        }
        return ControlTower.instance;
    }

    private ControlTower() {
        this.schedule = new Schedule();
    }

    public String toString() {
        return schedule.toString();
    }

    public static void main(String[] args) {
        // Control the runway(s) of a city airport
        //  - Planes coming in 7am - 1am
        //  - Planes going out 6am - 12pm
        //  - Time isolated for takeoff - 3 minutes
        //  - Time isolated for landing - 3 minutes
        //  - Assume clear weather for now
        //  - Assume no diversions to your airport for now
        Schedule sc = new Schedule();

        sc.addItinerary(new Itinerary(Airport.AUSTIN, Airport.CHICAGO_MIDWAY, Fleet.getByTailNumber(123), LocalDateTime.now().plusHours(1)));
        sc.addItinerary(new Itinerary(Airport.AUSTIN, Airport.NEW_YORK_JFK, Fleet.getByTailNumber(244), LocalDateTime.now().plusHours(3)));
        sc.addItinerary(new Itinerary(Airport.AUSTIN, Airport.CHICAGO_MIDWAY, Fleet.getByTailNumber(124), LocalDateTime.now().minusHours(1)));
        sc.addItinerary(new Itinerary(Airport.AUSTIN, Airport.NEW_YORK_JFK, Fleet.getByTailNumber(245), LocalDateTime.now().minusHours(2)));
        sc.addItinerary(new Itinerary(Airport.CHICAGO_MIDWAY, Airport.AUSTIN, Fleet.getByTailNumber(125), LocalDateTime.now().minusHours(1)));
        sc.addItinerary(new Itinerary(Airport.NEW_YORK_JFK, Airport.AUSTIN, Fleet.getByTailNumber(246), LocalDateTime.now().minusHours(2)));
        sc.addItinerary(new Itinerary(Airport.ATLANTA, Airport.CHICAGO_MIDWAY, Fleet.getByTailNumber(124), LocalDateTime.now().plusMinutes(5)));
        System.out.println(sc);

        System.out.println("Upcoming Flights in order - AUS");
        List<Itinerary> flights = sc.getUpcomingFlights(Airport.AUSTIN);
        for (Itinerary i: flights) {
            System.out.println(i);
        }
        System.out.println("Upcoming Flights in order - ATL");
        flights = sc.getUpcomingFlights(Airport.ATLANTA);
        for (Itinerary i: flights) {
            System.out.println(i);
        }
        System.out.println("Upcoming Flights in order - MDW");
        flights = sc.getUpcomingFlights(Airport.CHICAGO_MIDWAY);
        for (Itinerary i: flights) {
            System.out.println(i);
        }
    }
}
