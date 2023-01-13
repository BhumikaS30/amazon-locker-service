package com.amazon.locker.repositories;

import java.util.HashMap;
import java.util.Map;

import com.amazon.locker.models.Order;

public class OrderRepository {

    public static Map<String, Order> orderMap = new HashMap<>();

    public Order getOrder(String orderId) {
        return orderMap.get(orderId);
    }
}
