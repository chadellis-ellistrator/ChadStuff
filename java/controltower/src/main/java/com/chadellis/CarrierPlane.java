package com.chadellis;

public record CarrierPlane(Carrier carrier, Airplane airplane, int tailNumber) {
    public String toString() {
        return this.carrier.getCommonName() + " " + this.airplane + " tail " + this.tailNumber; 
    }
}

enum Carrier {
    DELTA("Delta"),
    SOUTHWEST("Southwest");

    private String commonName;

    private Carrier(String commonName) {
        this.commonName = commonName;
    }

    public String getCommonName() {
        return this.commonName;
    }
}
