package com.amazon.locker.repositories;

import java.util.ArrayList;
import java.util.List;

import com.amazon.locker.models.LockerLocation;

public class LockerLocationRepository {

    public static final List<LockerLocation> lockerLocations = new ArrayList<>();

    public static LockerLocation getLockerLocation(String lockerId) {
        //Assumption is list of locker locations in a given radius is fetched from service in 'lockerLocations'
        return lockerLocations.stream()
                              .filter(lockerLocation -> lockerId.equals(lockerLocation.getId()))
                              .findFirst()
                              .orElse(null);
    }
}
