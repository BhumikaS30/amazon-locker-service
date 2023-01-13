package com.amazon.locker.services;

import com.amazon.locker.models.Order;
import com.amazon.locker.repositories.OrderRepository;

public class OrderService {

    private OrderRepository orderRepository;

    public Order getOrder(String orderId) {
        return orderRepository.getOrder(orderId);
    }
}
