package com.amazon.locker.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import com.amazon.locker.models.GeoLocation;
import com.amazon.locker.models.Locker;
import com.amazon.locker.models.LockerSize;

import static com.amazon.locker.models.LockerStatus.AVAILABLE;

public class LockerRepository {

    public static List<Locker> lockers;
    public static Map<String, Locker> lockerMap;

    static {
        lockers = new ArrayList<>();
        lockerMap = new HashMap<>();
    }

    public static Locker getLocker(LockerSize lockerSize, GeoLocation lockerLocation) {
        //Assumption is a list of lockers in a given radius of the lockerLocation are fetched in 'lockers'
        return lockers.stream()
                      .filter(locker -> lockerSize.equals(locker.getLockerSize())
                                        && AVAILABLE.equals(locker.getLockerStatus()))
                      .findFirst()
                      .orElse(null);
    }
}
