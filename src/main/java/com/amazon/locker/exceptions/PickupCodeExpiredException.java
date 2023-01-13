package com.amazon.locker.exceptions;

public class PickupCodeExpiredException extends Exception{

    public PickupCodeExpiredException(String message) {
        super(message);
    }
}
