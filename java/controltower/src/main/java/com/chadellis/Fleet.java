package com.chadellis;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private static List<CarrierPlane> fleet = new ArrayList<>();

    private Fleet() {}

    static {
        fleet.add(new CarrierPlane(Carrier.DELTA, Airplane.BOEING_747, 123));
        fleet.add(new CarrierPlane(Carrier.DELTA, Airplane.BOEING_737_800, 124));
        fleet.add(new CarrierPlane(Carrier.DELTA, Airplane.AIRBUS_A320, 125));
        fleet.add(new CarrierPlane(Carrier.SOUTHWEST, Airplane.BOEING_737_800, 244));
        fleet.add(new CarrierPlane(Carrier.SOUTHWEST, Airplane.BOEING_737_800, 245));
        fleet.add(new CarrierPlane(Carrier.SOUTHWEST, Airplane.BOEING_737_MAX, 246));
    }

    public static CarrierPlane getByTailNumber(int tailNumber) {
        return fleet.stream().filter(cp -> cp.tailNumber() == tailNumber).findFirst().orElseThrow(() -> new IllegalArgumentException());
    }
}
