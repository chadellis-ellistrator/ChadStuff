package com.chadellis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Legs {
    private Map<Airport, List<Leg>> legsByOrigin = new HashMap<>();

    public Legs() {
        this.createLegs();
    }

    private void createLegs() {
        this.createLegPair(Airport.AUSTIN, Airport.ATLANTA, 813, 105);
        this.createLegPair(Airport.AUSTIN, Airport.CHICAGO_MIDWAY, 972, 125);
        this.createLegPair(Airport.AUSTIN, Airport.NEW_YORK_JFK, 1521, 202);
        this.createLegPair(Airport.ATLANTA, Airport.CHICAGO_MIDWAY, 591, 75);
    }

    private void createLegPair(Airport a1, Airport a2, int distanceInMiles, int durationInMinutes) {
        if (!this.legsByOrigin.containsKey(a1)) {
            this.legsByOrigin.put(a1, new ArrayList<Leg>());
        }
        if (!this.legsByOrigin.containsKey(a2)) {
            this.legsByOrigin.put(a2, new ArrayList<Leg>());
        }
        List<Leg> a1Legs = this.legsByOrigin.get(a1);
        if (!a1Legs.stream().anyMatch(l -> l.destination().equals(a2))) {
            a1Legs.add(new Leg(a2, distanceInMiles, durationInMinutes));
        }
        List<Leg> a2Legs = this.legsByOrigin.get(a2);
        if (!a2Legs.stream().anyMatch(l -> l.destination().equals(a1))) {
            a2Legs.add(new Leg(a1, distanceInMiles, durationInMinutes));
        }
    }

    public Optional<Leg> getShortestLeg(Airport airport) {
        return this.getLegByComparator(
            airport, 
            (l1, l2) -> Integer.valueOf(l1.durationInMinutes()).compareTo(l2.durationInMinutes())
        );
    }

    public Optional<Leg> getLongestLeg(Airport airport) {
        return this.getLegByComparator(
            airport,
            (l1, l2) -> Integer.valueOf(l2.durationInMinutes()).compareTo(l1.durationInMinutes())
        );
    }

    private Optional<Leg> getLegByComparator(Airport airport, Comparator<Leg> c) {
        return this.legsByOrigin.getOrDefault(airport, Collections.emptyList())
            .stream()
            .sorted(c)
            .findFirst();
    }

    public Optional<Leg> getLeg(Airport origin, Airport destination) {
        return this.legsByOrigin.getOrDefault(origin, Collections.emptyList())
            .stream().filter(l -> l.destination().equals(destination)).findFirst();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Legs");
        for (Airport c: this.legsByOrigin.keySet()) {
            sb.append("\n" + c.getFullName());
            for (Leg l: this.legsByOrigin.get(c)) {
                sb.append("\n" + l.toString());
            }
        }
        return sb.toString();
    }
}
