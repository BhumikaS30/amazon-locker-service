package com.amazon.locker.services;

import com.amazon.locker.models.LockerLocation;
import com.amazon.locker.repositories.LockerLocationRepository;

public class LockerLocationService {


    public static LockerLocation getLockerLocation(String lockerId) {
        return LockerLocationRepository.getLockerLocation(lockerId);
    }

}
