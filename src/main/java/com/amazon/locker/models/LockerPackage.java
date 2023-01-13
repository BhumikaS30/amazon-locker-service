package com.amazon.locker.models;

import java.time.LocalDateTime;

import com.amazon.locker.exceptions.PickupCodeExpiredException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerPackage {

    private final Integer CODE_VALIDITY_DAYS = 3;

    private String id;

    private String lockerId;

    private String orderId;

    private LocalDateTime packageDeliveryTime;

    private String code;

    public boolean isCodeValid(LocalDateTime currentTime) throws PickupCodeExpiredException {
        if (currentTime.compareTo(this.packageDeliveryTime) > CODE_VALIDITY_DAYS) {
            throw new PickupCodeExpiredException("Pickup code expired.");
        }
        return true;
    }

    public boolean verifyCode(String code) {
        return this.code.equals(code);
    }
}
