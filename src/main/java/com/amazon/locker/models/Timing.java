package com.amazon.locker.models;

import java.sql.Time;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Timing {

    private Time openingTime;
    private Time closingTime;

    public Timing(String openingTime, String closingTime) {
        this.openingTime = Time.valueOf(openingTime);
        this.closingTime = Time.valueOf(closingTime);
    }
}
