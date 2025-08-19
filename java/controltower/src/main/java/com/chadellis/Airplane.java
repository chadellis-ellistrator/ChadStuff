package com.chadellis;

enum Airplane {
    BOEING_737_800(Make.BOEING, "737-800", 180, 520),
    BOEING_737_MAX(Make.BOEING, "737-MAX", 200, 520),
    BOEING_747(Make.BOEING, "747", 450, 575),
    AIRBUS_A320(Make.AIRBUS, "A320", 175, 520),
    AIRBUS_A380(Make.AIRBUS, "A380", 525, 560);

    private Make make;
    private String model;
    private int capacityPassengers;
    private int topSpeed;

    private Airplane(Make make, String model, int capacityPassengers, int topSpeed) {
        this.make = make;
        this.model = model;
        this.capacityPassengers = capacityPassengers;
        this.topSpeed = topSpeed;
    }

    public String toString() {
        return this.make.getCommonName() + " " + this.model;
    }
}

enum Make {
    BOEING("Boeing"),
    AIRBUS("AirBus");

    private String commonName;

    private Make(String commonName) {
        this.commonName = commonName;
    }

    public String getCommonName() {
        return this.commonName;
    }
}
