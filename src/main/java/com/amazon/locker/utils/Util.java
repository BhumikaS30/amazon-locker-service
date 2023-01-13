package com.amazon.locker.utils;

import com.amazon.locker.exceptions.PackageSizeMappingException;
import com.amazon.locker.models.LockerSize;

import static com.amazon.locker.models.LockerSize.L;
import static com.amazon.locker.models.LockerSize.M;
import static com.amazon.locker.models.LockerSize.XL;
import static com.amazon.locker.models.LockerSize.XS;
import static com.amazon.locker.models.LockerSize.XXL;

public class Util {

    public static LockerSize getLockerSizeForPack(double packSize) throws PackageSizeMappingException {
        if (packSize > 10 && packSize <= 20) {
            return XS;
        } else if (packSize > 20 && packSize <= 40) {
            return M;
        } else if (packSize > 40 && packSize <= 50) {
            return L;
        } else if (packSize > 50 && packSize <= 70) {
            return XL;
        } else if (packSize > 70 && packSize <= 100) {
            return XXL;
        } else {
            throw new PackageSizeMappingException("Package size more than" +
                                                  " the largest available locker.");
        }
    }

}
