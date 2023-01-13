package com.amazon.locker.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LockerLocation {

    private String id;

    private GeoLocation geoLocation;

    private LocationTiming locationTiming;

    private List<Locker> lockers = new ArrayList<>();

}
