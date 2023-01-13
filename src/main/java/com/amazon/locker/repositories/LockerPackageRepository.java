package com.amazon.locker.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazon.locker.models.LockerPackage;

public class LockerPackageRepository {

    private static List<LockerPackage> lockerPackages = new ArrayList<>();

    public static LockerPackage getLockerByLockerId(String lockerId) {
        return lockerPackages.stream()
            .filter(lockerPackage -> lockerPackage.getLockerId().equals(lockerId))
            .findFirst()
            .orElse(null);
    }

}
