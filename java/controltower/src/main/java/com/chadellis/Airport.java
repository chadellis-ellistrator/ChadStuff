package com.chadellis;

public enum Airport {
    AUSTIN("Austin", "AUS"),
    ATLANTA("Atlanta", "ATL"),
    CHICAGO_MIDWAY("Chicago Midway", "MDW"),
    NEW_YORK_JFK("New York JFK", "JFK");

    private String fullName;
    private String id;

    private Airport(String fullName, String id) {
        this.fullName = fullName;
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getIdentifier() {
        return this.id;
    }

    public String toString() {
        return this.id + " - " + this.fullName;
    }
}