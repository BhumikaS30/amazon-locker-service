package com.amazon.locker.services;

import com.amazon.locker.models.LockerPackage;
import com.amazon.locker.models.Notification;
import com.amazon.locker.repositories.NotificationRepository;

public class NotificationService {

    private final CustomerService customerService = new CustomerService();

    public void notifyCustomerOrder(LockerPackage lockerPackage) {
        String customerId = customerService.getCustomerIdForOrder(lockerPackage.getOrderId());
        Notification notification = new Notification(customerId, lockerPackage.getOrderId(),
                                                     lockerPackage.getLockerId(), lockerPackage.getCode());
        NotificationRepository.notificationMap.put(lockerPackage.getOrderId(), notification);
        System.out.printf("Customer %s notified for order %s " +
                          " in locker %s with pin %s", customerId,
                          lockerPackage.getOrderId(),
                          lockerPackage.getLockerId(),
                          lockerPackage.getCode()
        );
    }
}
