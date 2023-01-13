package com.amazon.locker.models;

import lombok.Data;

@Data
public class Locker {

    private String id;

    private String locationId;

    private LockerStatus lockerStatus;

    private LockerSize lockerSize;
}
