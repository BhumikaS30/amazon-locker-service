package com.amazon.locker.services;

import java.util.UUID;

public class CustomerService {

    public String getCustomerIdForOrder(String orderId) {
        return UUID.randomUUID().toString();
    }
}
