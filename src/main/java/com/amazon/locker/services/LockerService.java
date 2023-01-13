package com.amazon.locker.services;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.amazon.locker.exceptions.LockeCodeMisMatchException;
import com.amazon.locker.exceptions.LockerNotFoundException;
import com.amazon.locker.exceptions.PackPickTimeExceededException;
import com.amazon.locker.exceptions.PickupCodeExpiredException;
import com.amazon.locker.models.GeoLocation;
import com.amazon.locker.models.LocationTiming;
import com.amazon.locker.models.Locker;
import com.amazon.locker.models.LockerLocation;
import com.amazon.locker.models.LockerPackage;
import com.amazon.locker.models.LockerSize;
import com.amazon.locker.models.Timing;
import com.amazon.locker.repositories.LockerLocationRepository;
import com.amazon.locker.repositories.LockerPackageRepository;
import com.amazon.locker.repositories.LockerRepository;

import static com.amazon.locker.models.LockerStatus.AVAILABLE;
import static com.amazon.locker.models.LockerStatus.BOOKED;
import static com.amazon.locker.models.LockerStatus.CLOSED;
import static java.util.Objects.isNull;

public class LockerService {


    public Locker getLocker(LockerSize lockerSize, GeoLocation lockerLocation) {
        return checkForAvailableLocker(lockerSize, lockerLocation);
    }

    private Locker checkForAvailableLocker(LockerSize lockerSize, GeoLocation lockerLocation) {
        Locker locker = LockerRepository.getLocker(lockerSize, lockerLocation);
        locker.setLockerStatus(BOOKED);
        return locker;
    }

    public void pickFromLocker(String lockerId, String code, LocalDateTime localDateTime) throws PickupCodeExpiredException, PackPickTimeExceededException, LockeCodeMisMatchException, LockerNotFoundException {

        LockerPackage lockerPackage = LockerPackageRepository.getLockerByLockerId(lockerId);
        if (isNull(lockerPackage)) {
            throw new LockerNotFoundException("Locker with given locker Id not found");
        }
        if (!lockerPackage.verifyCode(code) && !lockerPackage.isCodeValid(localDateTime)) {
            throw new LockeCodeMisMatchException("Locker code mismatch");
        }
        Locker locker = LockerRepository.lockerMap.get(lockerId);
        if (canPickUpFromLocker(locker, localDateTime)) {
            locker.setLockerStatus(AVAILABLE);
            lockerPackage.setCode(null);
        } else {
            lockerPackage.setCode(null);
            throw new PackPickTimeExceededException("Package not picked for x days");
        }
    }

    private boolean canPickUpFromLocker(Locker locker, LocalDateTime localDateTime) {

        LockerLocation lockerLocation = LockerLocationRepository.getLockerLocation(locker.getId());
        LocationTiming locationTiming = lockerLocation.getLocationTiming();
        Timing timing = locationTiming.getTimingMap().get(localDateTime.getDayOfWeek());
        Time currentTime = Time.valueOf(getTimeFromDate(localDateTime));
        if (currentTime.after(timing.getOpeningTime()) && currentTime.before(timing.getClosingTime())) {
            return true;
        }
        return false;
    }

    private String getTimeFromDate(LocalDateTime localDateTime) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = localDateFormat.format(new Date());
        return time;
    }
}
