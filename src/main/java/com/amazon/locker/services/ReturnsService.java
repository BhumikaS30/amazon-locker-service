package com.amazon.locker.services;

import com.amazon.locker.models.Item;
import com.amazon.locker.models.Locker;
import com.amazon.locker.models.LockerStatus;

import static com.amazon.locker.models.LockerStatus.CLOSED;

public class ReturnsService {

    public void returnItemsToLocker(Item item, Locker locker) {locker.setLockerStatus(CLOSED);}

}
