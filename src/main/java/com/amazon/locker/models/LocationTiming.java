package com.amazon.locker.models;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationTiming {

    private Map<DayOfWeek, Timing> timingMap = new HashMap<>();
}
